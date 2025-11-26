package org.example.medical_clinic_management_system.mapper.schedule;

import org.example.medical_clinic_management_system.dto.schedule.ScheduleDto;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Receptionist;
import org.example.medical_clinic_management_system.model.schedule.Schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper
{

    public ScheduleDto toDto(Schedule entity) {
        ScheduleDto dto = new ScheduleDto();
        dto.setId(entity.getId());
        dto.setMedicalStaffId(entity.getMedicalStaff().getId());
        dto.setReceptionistId(entity.getCreatedBy().getId());
        dto.setDate(entity.getDate());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setType(entity.getType());
        return dto;
    }

    public Schedule toEntity(ScheduleDto dto, MedicalStaff medicalStaff, Receptionist receptionist) {
        return Schedule.builder()
                .id(dto.getId())
                .medicalStaff(medicalStaff)
                .createdBy(receptionist)
                .date(dto.getDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .type(dto.getType())
                .build();
    }

}
