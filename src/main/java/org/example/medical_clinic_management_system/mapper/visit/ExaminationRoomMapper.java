package org.example.medical_clinic_management_system.mapper.visit;

import org.example.medical_clinic_management_system.dto.visit.ExaminationRoomDetailsDto;
import org.example.medical_clinic_management_system.dto.visit.ExaminationRoomDto;
import org.example.medical_clinic_management_system.dto.visit.ExaminationRoomRequestDto;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExaminationRoomMapper
{

    public ExaminationRoomDetailsDto toDetailsDto(ExaminationRoom entity) {
        if (entity == null) {
            return null;
        }

        ExaminationRoomDetailsDto dto = new ExaminationRoomDetailsDto();
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setPurpose(entity.getPurpose());
        dto.setStatus(entity.getStatus());

        return dto;
    }


    public List<ExaminationRoomDetailsDto> toDetailsDtoList(List<ExaminationRoom> entities) {
        return entities.stream()
                .map(this::toDetailsDto)
                .collect(Collectors.toList());
    }

    public ExaminationRoom toEntity(ExaminationRoomRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return ExaminationRoom.builder()
                .number(dto.getNumber())
                .purpose(dto.getPurpose())
                .status(dto.getStatus())
                .build();
    }

    public void updateEntity(ExaminationRoom entity, ExaminationRoomRequestDto dto)
    {

        if (dto.getNumber() != null)
        {
            entity.setNumber(dto.getNumber());
        }

        if (dto.getPurpose() != null)
        {
            entity.setPurpose(dto.getPurpose());
        }

        if (dto.getStatus() != null)
        {
            entity.setStatus(dto.getStatus());
        }

    }





}
