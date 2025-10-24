package org.example.medical_clinic_management_system.mapper.visit;

import org.example.medical_clinic_management_system.dto.visit.ExaminationRoomDto;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;
import org.springframework.stereotype.Component;

@Component
public class ExaminationRoomMapper
{
    public ExaminationRoomDto toDto(ExaminationRoom entity) {
        return ExaminationRoomDto.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .purpose(entity.getPurpose())
                .location(entity.getLocation())
                .status(entity.getStatus())
                .build();
    }

    public ExaminationRoom toEntity(ExaminationRoomDto dto) {
        return ExaminationRoom.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .purpose(dto.getPurpose())
                .location(dto.getLocation())
                .status(dto.getStatus())
                .build();
    }


}
