package org.example.medical_clinic_management_system.mapper.service;

import org.example.medical_clinic_management_system.dto.service.MedicalServiceDto;
import org.example.medical_clinic_management_system.model.service.MedicalService;
import org.springframework.stereotype.Component;

@Component
public class MedicalServiceMapper
{

    public MedicalServiceDto toDto(MedicalService entity) {
        return MedicalServiceDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .duration(entity.getDuration())
                .category(entity.getCategory())
                .build();
    }

    public MedicalService toEntity(MedicalServiceDto dto) {
        return MedicalService.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .duration(dto.getDuration())
                .category(dto.getCategory())
                .build();
    }



}
