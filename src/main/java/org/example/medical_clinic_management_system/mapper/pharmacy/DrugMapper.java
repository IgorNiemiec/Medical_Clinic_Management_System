package org.example.medical_clinic_management_system.mapper.pharmacy;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.pharmacy.DrugDetailsDto;
import org.example.medical_clinic_management_system.dto.pharmacy.DrugRequestDto;
import org.example.medical_clinic_management_system.model.pharmacy.Drug;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DrugMapper
{

    public DrugDetailsDto toDetailsDto(Drug entity) {
        if (entity == null) {
            return null;
        }

        DrugDetailsDto dto = new DrugDetailsDto();
        dto.setId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setCommonName(entity.getCommonName());
        dto.setForm(entity.getForm());
        dto.setAtcCode(entity.getAtcCode());
        dto.setGtinNumber(entity.getGtinNumber());

        return dto;
    }

    public List<DrugDetailsDto> toDetailsDtoList(List<Drug> entities) {
        return entities.stream()
                .map(this::toDetailsDto)
                .collect(Collectors.toList());
    }

    public Drug toEntity(DrugRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Drug.builder()
                .productName(dto.getProductName())
                .commonName(dto.getCommonName())
                .form(dto.getForm())
                .atcCode(dto.getAtcCode())
                .gtinNumber(dto.getGtinNumber())
                .build();
    }

    public void updateEntityFromDto(DrugRequestDto dto, Drug entity) {
        if (dto == null || entity == null) {
            return;
        }

        entity.setProductName(dto.getProductName());
        entity.setCommonName(dto.getCommonName());
        entity.setForm(dto.getForm());
        entity.setAtcCode(dto.getAtcCode());
        entity.setGtinNumber(dto.getGtinNumber());

    }



}
