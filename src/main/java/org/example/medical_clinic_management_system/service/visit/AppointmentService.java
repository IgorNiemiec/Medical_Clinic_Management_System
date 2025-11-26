package org.example.medical_clinic_management_system.service.visit;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.AppointmentDetailsDto;
import org.example.medical_clinic_management_system.dto.visit.AppointmentRequestDto;
import org.example.medical_clinic_management_system.mapper.visit.AppointmentMapper;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;
import org.example.medical_clinic_management_system.repository.person.MedicalStaffRepository;
import org.example.medical_clinic_management_system.repository.person.PatientRepository;
import org.example.medical_clinic_management_system.repository.visit.AppointmentRepository;
import org.example.medical_clinic_management_system.repository.visit.ExaminationRoomRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService extends org.example.medical_clinic_management_system.model.medicalService.AppointmentService {

    private static final int APPOINTMENT_DURATION_MINUTES = 30;

    private final AppointmentServiceRepository appointmentServiceRepository;
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    private final PatientRepository patientRepository;
    private final MedicalStaffRepository medicalStaffRepository;
    private final ExaminationRoomRepository examinationRoomRepository;

    private Appointment getRelatedEntities(AppointmentRequestDto dto, Long appointmentId) {

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Pacjent o ID " + dto.getPatientId() + " nie istnieje."));

        MedicalStaff staff = medicalStaffRepository.findById(dto.getMedicalStaffId())
                .orElseThrow(() -> new IllegalArgumentException("Personel medyczny o ID " + dto.getMedicalStaffId() + " nie istnieje."));

        ExaminationRoom room = examinationRoomRepository.findById(dto.getExaminationRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Gabinet o ID " + dto.getExaminationRoomId() + " nie istnieje."));


        checkAppointmentCollision(staff.getId(), room.getId(), dto.getTime(), appointmentId);


        Appointment appointment = appointmentMapper.toEntity(dto);
        appointment.setPatient(patient);
        appointment.setMedicalStaff(staff);
        appointment.setExaminationRoom(room);

        return appointment;
    }


    private void checkAppointmentCollision(Long medicalStaffId, Long examinationRoomId, LocalDateTime startTime, Long excludedAppointmentId) {
        LocalDateTime endTime = startTime.plusMinutes(APPOINTMENT_DURATION_MINUTES);

        List<Appointment> conflicts = appointmentRepository.findConflictingAppointments(
                medicalStaffId,
                examinationRoomId,
                startTime,
                endTime,
                excludedAppointmentId
        );

        if (!conflicts.isEmpty()) {
            Appointment conflict = conflicts.get(0);
            String message;
            if (conflict.getMedicalStaff().getId().equals(medicalStaffId)) {
                message = "Kolizja: Personel medyczny (ID: " + medicalStaffId + ") jest już zajęty w tym terminie (Wizyta ID: " + conflict.getId() + ").";
            } else {
                message = "Kolizja: Gabinet (ID: " + examinationRoomId + ") jest już zajęty w tym terminie (Wizyta ID: " + conflict.getId() + ").";
            }
            throw new IllegalStateException(message);
        }
    }

    @Transactional
    public AppointmentDetailsDto createAppointment(AppointmentRequestDto requestDto) {

        Appointment appointment = getRelatedEntities(requestDto, 0L);

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDetailsDto(savedAppointment);
    }

    @Transactional
    public AppointmentDetailsDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wizyta o ID: " + id + " nie została znaleziona."));
        return appointmentMapper.toDetailsDto(appointment);
    }

    @Transactional
    public List<AppointmentDetailsDto> getStaffScheduleForDay(Long medicalStaffId, java.time.LocalDate date) {
        // Walidacja istnienia personelu
        medicalStaffRepository.findById(medicalStaffId)
                .orElseThrow(() -> new IllegalArgumentException("Personel medyczny o ID " + medicalStaffId + " nie istnieje."));

        List<Appointment> appointments = appointmentRepository.findByMedicalStaffIdAndDateOrderByTimeAsc(medicalStaffId, date);
        return appointmentMapper.toDetailsDtoList(appointments);
    }

    @Transactional
    public AppointmentDetailsDto updateAppointment(Long id, AppointmentRequestDto requestDto) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wizyta o ID: " + id + " nie została znaleziona."));


        Appointment updatedAppointment = getRelatedEntities(requestDto, id);


        appointmentMapper.updateEntity(existingAppointment, requestDto);
        existingAppointment.setPatient(updatedAppointment.getPatient());
        existingAppointment.setMedicalStaff(updatedAppointment.getMedicalStaff());
        existingAppointment.setExaminationRoom(updatedAppointment.getExaminationRoom());


        existingAppointment.setDate(updatedAppointment.getTime().toLocalDate());

        Appointment savedAppointment = appointmentRepository.save(existingAppointment);
        return appointmentMapper.toDetailsDto(savedAppointment);
    }

    @Transactional
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Nie można usunąć. Wizyta o ID: " + id + " nie istnieje.");
        }
        appointmentRepository.deleteById(id);
    }

    @Transactional
    public void markPastAppointmentsAsNoShow() {

        java.time.LocalDate yesterday = java.time.LocalDate.now().minusDays(1);

        List<Appointment> scheduledAppointments = appointmentRepository.findByDateBeforeAndStatus(yesterday, Appointment.AppointmentStatus.SCHEDULED);
        List<Appointment> confirmedAppointments = appointmentRepository.findByDateBeforeAndStatus(yesterday, Appointment.AppointmentStatus.CONFIRMED);

        scheduledAppointments.addAll(confirmedAppointments);

        for (Appointment appointment : scheduledAppointments) {
            appointment.setStatus(Appointment.AppointmentStatus.NO_SHOW);
            appointmentRepository.save(appointment);
        }
    }

    @Transactional
    public BigDecimal calculateTotalCost(Long appointmentId) {
        if (!appointmentRepository.existsById(appointmentId)) {
            throw new RuntimeException("Wizyta");
        }
        return appointmentServiceRepository.calculateTotalCostForAppointment(appointmentId);
    }

    @Transactional
    public void deleteAppointmentService(Long id) {
        if (!appointmentServiceRepository.existsById(id)) {
            throw new RuntimeException("Pozycja Usługi Wizyty");
        }
        appointmentServiceRepository.deleteById(id);
    }



}
