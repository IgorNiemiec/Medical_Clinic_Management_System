package org.example.medical_clinic_management_system.dto.visit;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AppointmentRequestDto
{

    @NotNull(message = "{validation.appointment.patientId.notNull}")
    @Min(value = 1, message = "{validation.appointment.patientId.min}")
    private Long patientId;

    @NotNull(message = "{validation.appointment.medicalStaffId.notNull}")
    @Min(value = 1, message = "{validation.appointment.medicalStaffId.min}")
    private Long medicalStaffId;

    @NotNull(message = "{validation.appointment.examinationRoomId.notNull}")
    @Min(value = 1, message = "{validation.appointment.examinationRoomId.min}")
    private Long examinationRoomId;

    @NotNull(message = "{validation.appointment.date.notNull}")
    @FutureOrPresent(message = "{validation.appointment.date.futureOrPresent}")
    private LocalDate date;

    @NotNull(message = "{validation.appointment.time.notNull}")
    private LocalDateTime time;

    @NotNull(message = "{validation.appointment.type.notNull}")
    private Appointment.AppointmentType type;

    @NotNull(message = "{validation.appointment.status.notNull}")
    private Appointment.AppointmentStatus status;

    @Size(max = 2000, message = "{validation.appointment.description.size}")
    private String description;

}
