package org.example.medical_clinic_management_system.dto.record;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
public class PatientCardDto
{
    private Long id;

    @NotNull(message = "Patient ID cannot be null")
    private Long patientId;

    @NotNull(message = "Creation date is required")
    @PastOrPresent(message = "Creation date cannot be in the future")
    private LocalDate createdAt;

    @NotBlank(message = "Medical history cannot be blank")
    @Size(min = 5, max = 2000, message = "Medical history must be between 5 and 2000 characters")
    private String medicalHistory;

    @Size(max = 1000, message = "Allergies description cannot exceed 1000 characters")
    private String allergies;
}
