package org.example.medical_clinic_management_system.mapper.schedule;

import org.example.medical_clinic_management_system.dto.schedule.EmployeeScheduleDto;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.schedule.EmployeeSchedule;
import org.springframework.stereotype.Component;

@Component
public class EmployeeScheduleMapper
{

    public EmployeeScheduleDto toDto(EmployeeSchedule entity) {
        EmployeeScheduleDto dto = new EmployeeScheduleDto();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setDate(entity.getDate());
        dto.setType(entity.getType());
        return dto;
    }

    public EmployeeSchedule toEntity(EmployeeScheduleDto dto, MedicalStaff employee) {
        return EmployeeSchedule.builder()
                .id(dto.getId())
                .employee(employee)
                .date(dto.getDate())
                .type(dto.getType())
                .build();
    }

}
