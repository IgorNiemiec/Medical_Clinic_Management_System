package org.example.medical_clinic_management_system.dto.schedule;

import lombok.Data;
import org.example.medical_clinic_management_system.model.schedule.EmployeeSchedule.ScheduleType;

import java.time.LocalDate;

@Data
public class EmployeeScheduleDto
{
    private Long id;
    private Long employeeId;
    private LocalDate date;
    private ScheduleType type;
}
