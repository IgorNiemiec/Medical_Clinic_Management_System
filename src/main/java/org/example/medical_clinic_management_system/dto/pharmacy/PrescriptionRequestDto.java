package org.example.medical_clinic_management_system.dto.pharmacy;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionRequestDto
{

    @NotNull(message = "{prescription.patientId.notNull}")
    private Long patientId;

    @NotNull(message = "{prescription.doctorId.notNull}")
    private Long doctorId;

    @NotNull(message = "{prescription.drugId.notNull}")
    private Long drugId;

    @NotNull(message = "{prescription.expirationDate.notNull}")
    @Future(message = "{prescription.expirationDate.future}")
    private LocalDate expirationDate;

    @NotBlank(message = "{prescription.dosageInstructions.notBlank}")
    private String dosageInstructions;

    @NotNull(message = "{prescription.quantity.notNull}")
    @Min(value = 1, message = "{prescription.quantity.min}")
    private Integer quantity;

}
