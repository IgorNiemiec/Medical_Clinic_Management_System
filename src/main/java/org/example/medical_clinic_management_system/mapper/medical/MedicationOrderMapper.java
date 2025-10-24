package org.example.medical_clinic_management_system.mapper.medical;

import org.example.medical_clinic_management_system.dto.medical.MedicationOrderDto;
import org.example.medical_clinic_management_system.model.medical.MedicationOrder;
import org.example.medical_clinic_management_system.model.medical.MedicinalProduct;
import org.springframework.stereotype.Component;

@Component
public class MedicationOrderMapper
{
    public MedicationOrderDto toDto(MedicationOrder entity) {
        MedicationOrderDto dto = new MedicationOrderDto();
        dto.setId(entity.getId());
        dto.setProductId(entity.getProduct().getId());
        dto.setDate(entity.getDate());
        dto.setQuantity(entity.getQuantity());
        dto.setStatus(entity.getStatus());
        dto.setSupplier(entity.getSupplier());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public MedicationOrder toEntity(MedicationOrderDto dto, MedicinalProduct product) {
        return MedicationOrder.builder()
                .id(dto.getId())
                .product(product)
                .date(dto.getDate())
                .quantity(dto.getQuantity())
                .status(dto.getStatus())
                .supplier(dto.getSupplier())
                .price(dto.getPrice())
                .build();
    }
}
