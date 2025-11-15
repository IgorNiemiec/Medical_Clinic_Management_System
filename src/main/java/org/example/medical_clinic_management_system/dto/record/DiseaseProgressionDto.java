package org.example.medical_clinic_management_system.dto.record;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
public class DiseaseProgressionDto
{
    private Long id;

    @NotNull(message = "Patient card ID cannot be null")
    private Long patientCardId;

    @NotNull(message = "Disease ID cannot be null")
    private Long diseaseId;

    @NotNull(message = "Diagnosis date is required")
    @PastOrPresent(message = "Diagnosis date cannot be in the future")
    private LocalDate diagnosisDate;

    @NotBlank(message = "Symptoms cannot be blank")
    @Size(min = 3, max = 1000, message = "Symptoms must be between 3 and 1000 characters")
    private String symptoms;

    @NotBlank(message = "Treatment cannot be blank")
    @Size(min = 3, max = 1000, message = "Treatment must be between 3 and 1000 characters")
    private String treatment;
}
