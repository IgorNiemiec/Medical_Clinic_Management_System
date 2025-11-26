package org.example.medical_clinic_management_system.mapper.record;

import org.example.medical_clinic_management_system.dto.record.ICDCodeDto;
import org.example.medical_clinic_management_system.model.record.ICDCode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ICDCodeMapper
{

    public ICDCode toEntity(ICDCodeDto dto) {
        if (dto == null) {
            return null;
        }

        return ICDCode.builder()
                .code(dto.getCode())
                .namePl(dto.getNamePl())
                .nameEn(dto.getNameEn())
                .build();
    }

    public ICDCodeDto toDto(ICDCode entity) {
        if (entity == null) {
            return null;
        }

        ICDCodeDto dto = new ICDCodeDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setNamePl(entity.getNamePl());
        dto.setNameEn(entity.getNameEn());
        return dto;
    }


    public List<ICDCodeDto> toDtoList(List<ICDCode> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void updateEntity(ICDCode entity, ICDCodeDto dto) {
        entity.setCode(dto.getCode());
        entity.setNamePl(dto.getNamePl());
        entity.setNameEn(dto.getNameEn());
    }




}
