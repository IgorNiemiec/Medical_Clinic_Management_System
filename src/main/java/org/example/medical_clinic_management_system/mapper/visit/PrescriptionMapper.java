package org.example.medical_clinic_management_system.mapper.visit;

import org.example.medical_clinic_management_system.dto.visit.PrescriptionDto;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.example.medical_clinic_management_system.model.visit.Prescription;
import org.springframework.stereotype.Component;

@Component
public class PrescriptionMapper
{
    public PrescriptionDto toDto(Prescription entity) {
        return PrescriptionDto.builder()
                .id(entity.getId())
                .appointmentId(entity.getAppointment().getId())
                .patientId(entity.getPatient().getId())
                .doctorId(entity.getDoctor().getId())
                .issuedDate(entity.getIssuedDate())
                .recommendations(entity.getRecommendations())
                .build();
    }

    public Prescription toEntity(PrescriptionDto dto) {
        return Prescription.builder()
                .id(dto.getId())
                .appointment(Appointment.builder().id(dto.getAppointmentId()).build())
                .patient(Patient.builder().id(dto.getPatientId()).build())
                .doctor(MedicalStaff.builder().id(dto.getDoctorId()).build())
                .issuedDate(dto.getIssuedDate())
                .recommendations(dto.getRecommendations())
                .build();
    }


}
