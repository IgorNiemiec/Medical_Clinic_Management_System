package org.example.medical_clinic_management_system.dto.schedule;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.example.medical_clinic_management_system.model.schedule.EmployeeSchedule.ScheduleType;

import java.time.LocalDate;

@Data
public class EmployeeScheduleDto {
    private Long id;

    @NotNull(message = "{validation.employeeSchedule.employeeId.notNull}")
    private Long employeeId;

    @NotNull(message = "{validation.employeeSchedule.date.notNull}")
    @FutureOrPresent(message = "{validation.employeeSchedule.date.futureOrPresent}")
    private LocalDate date;

    @NotNull(message = "{validation.employeeSchedule.type.notNull}")
    private ScheduleType type;
}
