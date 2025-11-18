package org.example.medical_clinic_management_system.dto.record;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PatientCardDto {
    private Long id;

    @NotNull(message = "{validation.patientCard.patientId.notNull}")
    private Long patientId;

    @NotNull(message = "{validation.patientCard.createdAt.notNull}")
    @PastOrPresent(message = "{validation.patientCard.createdAt.pastOrPresent}")
    private LocalDate createdAt;

    @NotBlank(message = "{validation.patientCard.medicalHistory.notBlank}")
    @Size(min = 5, max = 2000, message = "{validation.patientCard.medicalHistory.size}")
    private String medicalHistory;

    @Size(max = 1000, message = "{validation.patientCard.allergies.size}")
    private String allergies;
}
