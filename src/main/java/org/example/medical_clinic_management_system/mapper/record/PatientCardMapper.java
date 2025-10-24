package org.example.medical_clinic_management_system.mapper.record;

import org.example.medical_clinic_management_system.dto.record.PatientCardDto;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.record.PatientCard;
import org.springframework.stereotype.Component;

@Component
public class PatientCardMapper
{

    public PatientCardDto toDto(PatientCard entity) {
        PatientCardDto dto = new PatientCardDto();
        dto.setId(entity.getId());
        dto.setPatientId(entity.getPatient().getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setMedicalHistory(entity.getMedicalHistory());
        dto.setAllergies(entity.getAllergies());
        return dto;
    }

    public PatientCard toEntity(PatientCardDto dto, Patient patient) {
        return PatientCard.builder()
                .id(dto.getId())
                .patient(patient)
                .createdAt(dto.getCreatedAt())
                .medicalHistory(dto.getMedicalHistory())
                .allergies(dto.getAllergies())
                .build();
    }


}
