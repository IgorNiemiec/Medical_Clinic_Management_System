package org.example.medical_clinic_management_system.mapper.medicalService;

import org.example.medical_clinic_management_system.dto.medicalService.MedicalServiceDetailsDto;
import org.example.medical_clinic_management_system.dto.medicalService.MedicalServiceDto;
import org.example.medical_clinic_management_system.dto.medicalService.MedicalServiceRequestDto;
import org.example.medical_clinic_management_system.model.medicalService.MedicalService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicalServiceMapper
{

    public MedicalServiceDetailsDto toDetailsDto(MedicalService entity) {
        if (entity == null) {
            return null;
        }

        MedicalServiceDetailsDto dto = new MedicalServiceDetailsDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        return dto;
    }


    public List<MedicalServiceDetailsDto> toDetailsDtoList(List<MedicalService> entities) {
        return entities.stream()
                .map(this::toDetailsDto)
                .collect(Collectors.toList());
    }

    public MedicalService toEntity(MedicalServiceRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return MedicalService.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }


    public void updateEntity(MedicalService entity, MedicalServiceRequestDto dto) {

        if (dto.getTitle() != null) {
            entity.setTitle(dto.getTitle());
        }

        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            entity.setPrice(dto.getPrice());
        }
    }



}
