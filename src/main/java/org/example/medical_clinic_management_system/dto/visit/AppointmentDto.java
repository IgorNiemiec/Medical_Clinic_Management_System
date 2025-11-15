package org.example.medical_clinic_management_system.dto.visit;

import lombok.Builder;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.Appointment.Status;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AppointmentDto
{
    private Long id;

    @NotNull(message = "Receptionist ID cannot be null")
    private Long receptionistId;

    @NotNull(message = "Patient ID cannot be null")
    private Long patientId;

    @NotNull(message = "Medical staff ID cannot be null")
    private Long medicalStaffId;

    @NotNull(message = "Room ID cannot be null")
    private Long roomId;

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date cannot be in the past")
    private LocalDate date;

    @NotNull(message = "Appointment time is required")
    private LocalTime time;

    @NotBlank(message = "Appointment type cannot be blank")
    @Size(min = 3, max = 100, message = "Appointment type must be between 3 and 100 characters")
    private String type;

    @NotNull(message = "Status is required")
    private Status status;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;
}
