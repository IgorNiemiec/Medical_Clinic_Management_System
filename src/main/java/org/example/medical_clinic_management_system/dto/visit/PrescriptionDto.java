package org.example.medical_clinic_management_system.dto.visit;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PrescriptionDto
{
    private Long id;

    @NotNull(message = "Appointment ID cannot be null")
    private Long appointmentId;

    @NotNull(message = "Patient ID cannot be null")
    private Long patientId;

    @NotNull(message = "Doctor ID cannot be null")
    private Long doctorId;

    @NotNull(message = "Issued date is required")
    @PastOrPresent(message = "Issued date cannot be in the future")
    private LocalDate issuedDate;

    @NotBlank(message = "Recommendations cannot be blank")
    @Size(min = 5, max = 2000, message = "Recommendations must be between 5 and 2000 characters")
    private String recommendations;
}
