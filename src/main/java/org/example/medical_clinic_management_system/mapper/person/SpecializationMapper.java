package org.example.medical_clinic_management_system.mapper.person;

import org.example.medical_clinic_management_system.dto.person.SpecializationDto;
import org.example.medical_clinic_management_system.model.person.Specialization;
import org.springframework.stereotype.Component;

@Component
public class SpecializationMapper
{
    public SpecializationDto toDto(Specialization entity) {
        SpecializationDto dto = new SpecializationDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    public Specialization toEntity(SpecializationDto dto) {
        return Specialization.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

}
