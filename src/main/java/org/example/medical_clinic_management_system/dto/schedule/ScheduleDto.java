package org.example.medical_clinic_management_system.dto.schedule;

import lombok.Data;
import org.example.medical_clinic_management_system.model.schedule.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleDto
{

    private Long id;

    private Long medicalStaffId;

    private Long receptionistId;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private Schedule.ScheduleType type;
}
