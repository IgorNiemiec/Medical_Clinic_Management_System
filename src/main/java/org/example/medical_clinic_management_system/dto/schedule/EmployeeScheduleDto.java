package org.example.medical_clinic_management_system.dto.schedule;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.example.medical_clinic_management_system.model.schedule.EmployeeSchedule.ScheduleType;

import java.time.LocalDate;

@Data
public class EmployeeScheduleDto
{
    private Long id;

    @NotNull(message = "Employee ID cannot be null")
    private Long employeeId;

    @NotNull(message = "Schedule date is required")
    @FutureOrPresent(message = "Schedule date cannot be in the past")
    private LocalDate date;

    @NotNull(message = "Schedule type is required")
    private ScheduleType type;
}
