package org.example.medical_clinic_management_system.mapper.medical;

import org.example.medical_clinic_management_system.dto.medical.MedicinalProductDto;
import org.example.medical_clinic_management_system.model.medical.MedicinalProduct;
import org.springframework.stereotype.Component;

@Component
public class MedicinalProductMapper
{

    public MedicinalProductDto toDto(MedicinalProduct entity) {
        MedicinalProductDto dto = new MedicinalProductDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setComposition(entity.getComposition());
        dto.setManufacturer(entity.getManufacturer());
        dto.setPrice(entity.getPrice());
        dto.setForm(entity.getForm());
        dto.setCategory(entity.getCategory());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }

    public MedicinalProduct toEntity(MedicinalProductDto dto) {
        return MedicinalProduct.builder()
                .id(dto.getId())
                .name(dto.getName())
                .composition(dto.getComposition())
                .manufacturer(dto.getManufacturer())
                .price(dto.getPrice())
                .form(dto.getForm())
                .category(dto.getCategory())
                .quantity(dto.getQuantity())
                .build();
    }


}
