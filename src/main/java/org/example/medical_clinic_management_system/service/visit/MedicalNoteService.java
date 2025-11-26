package org.example.medical_clinic_management_system.service.visit;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.MedicalNoteDetailsDto;
import org.example.medical_clinic_management_system.dto.visit.MedicalNoteDto;
import org.example.medical_clinic_management_system.dto.visit.MedicalNoteRequestDto;
import org.example.medical_clinic_management_system.mapper.visit.MedicalNoteMapper;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.example.medical_clinic_management_system.model.visit.MedicalNote;
import org.example.medical_clinic_management_system.repository.person.MedicalStaffRepository;
import org.example.medical_clinic_management_system.repository.visit.AppointmentRepository;
import org.example.medical_clinic_management_system.repository.visit.MedicalNoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalNoteService
{
    private final MedicalNoteRepository medicalNoteRepository;
    private final MedicalNoteMapper medicalNoteMapper;

    private final AppointmentRepository appointmentRepository;
    private final MedicalStaffRepository medicalStaffRepository;

    private MedicalNote getRelatedEntitiesAndValidate(MedicalNoteRequestDto dto) {

        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Wizyta"));


        MedicalStaff staff = medicalStaffRepository.findById(dto.getMedicalStaffId())
                .orElseThrow(() -> new RuntimeException("Personel Medyczny"));


        MedicalNote note = medicalNoteMapper.toEntity(dto);
        note.setAppointment(appointment);
        note.setMedicalStaff(staff);

        return note;
    }


    @Transactional
    public MedicalNoteDetailsDto createMedicalNote(MedicalNoteRequestDto requestDto) {

        if (requestDto.getDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Nie można utworzyć notatki z przyszłą datą. Wizyta musi się odbyć.");
        }

        MedicalNote note = getRelatedEntitiesAndValidate(requestDto);

        MedicalNote savedNote = medicalNoteRepository.save(note);
        return medicalNoteMapper.toDetailsDto(savedNote);
    }

    @Transactional
    public MedicalNoteDetailsDto getMedicalNoteById(Long id) {
        MedicalNote note = medicalNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notatka Lekarska"));
        return medicalNoteMapper.toDetailsDto(note);
    }


    @Transactional
    public List<MedicalNoteDetailsDto> getNotesByAppointmentId(Long appointmentId) {

        appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Wizyta"));

        List<MedicalNote> notes = medicalNoteRepository.findByAppointmentIdOrderByCreatedAtAsc(appointmentId);
        return medicalNoteMapper.toDetailsDtoList(notes);
    }

    @Transactional
    public List<MedicalNoteDetailsDto> getNotesByPatientId(Long patientId) {

        List<MedicalNote> notes = medicalNoteRepository.findByAppointmentPatientIdOrderByDateDesc(patientId);
        return medicalNoteMapper.toDetailsDtoList(notes);
    }

    @Transactional
    public MedicalNoteDetailsDto updateMedicalNote(Long id, MedicalNoteRequestDto requestDto) {
        MedicalNote existingNote = medicalNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notatka Lekarska"));

        Appointment newAppointment = appointmentRepository.findById(requestDto.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Wizyta"));

        MedicalStaff newStaff = medicalStaffRepository.findById(requestDto.getMedicalStaffId())
                .orElseThrow(() -> new RuntimeException("Personel Medyczny"));


        if (!newAppointment.getMedicalStaff().getId().equals(newStaff.getId())) {
            throw new RuntimeException(
                    "Lekarz o ID: " + newStaff.getId() + " nie jest przypisany do wizyty ID: " + newAppointment.getId() + ". Nie można zaktualizować notatki."
            );
        }

        medicalNoteMapper.updateEntity(existingNote, requestDto);
        existingNote.setAppointment(newAppointment);
        existingNote.setMedicalStaff(newStaff);

        MedicalNote savedNote = medicalNoteRepository.save(existingNote);
        return medicalNoteMapper.toDetailsDto(savedNote);
    }

    @Transactional
    public void deleteMedicalNote(Long id) {
        if (!medicalNoteRepository.existsById(id)) {
            throw new RuntimeException("Notatka Lekarska");
        }
        medicalNoteRepository.deleteById(id);
    }





}
