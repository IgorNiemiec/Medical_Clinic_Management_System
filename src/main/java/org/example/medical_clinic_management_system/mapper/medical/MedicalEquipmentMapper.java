package org.example.medical_clinic_management_system.mapper.medical;

import org.example.medical_clinic_management_system.dto.medical.MedicalEquipmentDto;
import org.example.medical_clinic_management_system.model.medical.MedicalEquipment;
import org.springframework.stereotype.Component;

@Component
public class MedicalEquipmentMapper
{

    public MedicalEquipmentDto toDto(MedicalEquipment entity) {
        MedicalEquipmentDto dto = new MedicalEquipmentDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        dto.setQuantity(entity.getQuantity());
        dto.setLocation(entity.getLocation());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public MedicalEquipment toEntity(MedicalEquipmentDto dto) {
        return MedicalEquipment.builder()
                .id(dto.getId())
                .name(dto.getName())
                .type(dto.getType())
                .quantity(dto.getQuantity())
                .location(dto.getLocation())
                .status(dto.getStatus())
                .build();
    }


}
