package org.example.medical_clinic_management_system.dto.visit;

import lombok.Builder;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.Appointment.Status;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class AppointmentDto {
    private Long id;

    @NotNull(message = "{validation.appointment.receptionistId.notNull}")
    private Long receptionistId;

    @NotNull(message = "{validation.appointment.patientId.notNull}")
    private Long patientId;

    @NotNull(message = "{validation.appointment.medicalStaffId.notNull}")
    private Long medicalStaffId;

    @NotNull(message = "{validation.appointment.roomId.notNull}")
    private Long roomId;

    @NotNull(message = "{validation.appointment.date.notNull}")
    @FutureOrPresent(message = "{validation.appointment.date.futureOrPresent}")
    private LocalDate date;

    @NotNull(message = "{validation.appointment.time.notNull}")
    private LocalTime time;

    @NotBlank(message = "{validation.appointment.type.notBlank}")
    @Size(min = 3, max = 100, message = "{validation.appointment.type.size}")
    private String type;

    @NotNull(message = "{validation.appointment.status.notNull}")
    private Status status;

    @Size(max = 1000, message = "{validation.appointment.description.size}")
    private String description;
}
