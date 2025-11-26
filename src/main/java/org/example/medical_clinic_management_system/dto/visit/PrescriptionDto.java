package org.example.medical_clinic_management_system.dto.visit;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PrescriptionDto {
    private Long id;

    @NotNull(message = "{validation.prescription.patientId.notNull}")
    private Long patientId;

    @NotNull(message = "{validation.prescription.doctorId.notNull}")
    private Long doctorId;

    private Drug drug;

    private LocalDate expirationDate;

    private String dosageInstructions;

    private Integer quantity;
}