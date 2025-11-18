package org.example.medical_clinic_management_system.dto.medical;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.example.medical_clinic_management_system.model.medical.MedicationOrder.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MedicationOrderDto {
    private Long id;

    @NotNull(message = "{validation.medicationOrder.productId.notNull}")
    private Long productId;

    @NotNull(message = "{validation.medicationOrder.date.notNull}")
    private LocalDate date;

    @NotNull(message = "{validation.medicationOrder.quantity.notNull}")
    @Min(value = 1, message = "{validation.medicationOrder.quantity.min}")
    private Integer quantity;

    @NotNull(message = "{validation.medicationOrder.status.notNull}")
    private Status status;

    @NotBlank(message = "{validation.medicationOrder.supplier.notBlank}")
    @Size(min = 2, max = 100, message = "{validation.medicationOrder.supplier.size}")
    private String supplier;

    @NotNull(message = "{validation.medicationOrder.price.notNull}")
    @DecimalMin(value = "0.01", inclusive = true, message = "{validation.medicationOrder.price.min}")
    private BigDecimal price;
}
