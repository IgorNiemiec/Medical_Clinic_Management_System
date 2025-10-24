package org.example.medical_clinic_management_system.mapper.medical;

import org.example.medical_clinic_management_system.dto.medical.DiseaseDto;
import org.example.medical_clinic_management_system.model.medical.Disease;
import org.springframework.stereotype.Component;

@Component
public class DiseaseMapper
{

    public DiseaseDto toDto(Disease entity) {
        DiseaseDto dto = new DiseaseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setIcdCode(entity.getIcdCode());
        return dto;
    }

    public Disease toEntity(DiseaseDto dto) {
        return Disease.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .icdCode(dto.getIcdCode())
                .build();
    }


}
