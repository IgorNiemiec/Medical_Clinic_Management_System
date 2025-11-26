package org.example.medical_clinic_management_system.dto.visit;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AppointmentRequestDto
{

    private Long patientId;

    private Long medicalStaffId;

    private Long examinationRoomId;

    @FutureOrPresent(message = "Data wizyty musi być dzisiejsza lub przyszła.")
    private LocalDate date;

    private LocalDateTime time;

    private Appointment.AppointmentType type;

    private Appointment.AppointmentStatus status;

    private String description;

}
