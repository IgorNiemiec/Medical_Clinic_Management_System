package org.example.medical_clinic_management_system.dto.visit;

import lombok.Builder;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.Appointment.Status;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AppointmentDto
{
    private Long id;
    private Long receptionistId;
    private Long patientId;
    private Long medicalStaffId;
    private Long roomId;
    private LocalDate date;
    private LocalTime time;
    private String type;
    private Status status;
    private String description;
}
