package org.example.medical_clinic_management_system.mapper.person;

import org.example.medical_clinic_management_system.dto.person.PatientDto;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper
{

    public PatientDto toDto(Patient entity) {
        PatientDto dto = new PatientDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setAddress(entity.getAddress());
        dto.setPhone(entity.getPhone());
        dto.setPesel(entity.getPesel());
        dto.setGender(entity.getGender());
        return dto;
    }

    public Patient toEntity(PatientDto dto, User user) {
        return Patient.builder()
                .id(dto.getId())
                .user(user)
                .dateOfBirth(dto.getDateOfBirth())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .pesel(dto.getPesel())
                .gender(dto.getGender())
                .build();
    }



}
