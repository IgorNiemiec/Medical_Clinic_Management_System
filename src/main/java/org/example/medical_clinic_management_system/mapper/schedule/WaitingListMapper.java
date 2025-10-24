package org.example.medical_clinic_management_system.mapper.schedule;

import org.example.medical_clinic_management_system.dto.schedule.WaitingListDto;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.schedule.WaitingList;
import org.springframework.stereotype.Component;

@Component
public class WaitingListMapper
{

    public WaitingListDto toDto(WaitingList entity) {
        WaitingListDto dto = new WaitingListDto();
        dto.setId(entity.getId());
        dto.setPatientId(entity.getPatient().getId());
        dto.setAddedAt(entity.getAddedAt());
        dto.setPriority(entity.getPriority());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public WaitingList toEntity(WaitingListDto dto, Patient patient) {
        return WaitingList.builder()
                .id(dto.getId())
                .patient(patient)
                .addedAt(dto.getAddedAt())
                .priority(dto.getPriority())
                .status(dto.getStatus())
                .build();
    }


}
