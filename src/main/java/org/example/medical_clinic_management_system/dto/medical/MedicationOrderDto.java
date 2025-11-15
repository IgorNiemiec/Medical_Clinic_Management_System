package org.example.medical_clinic_management_system.dto.medical;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.example.medical_clinic_management_system.model.medical.MedicationOrder.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MedicationOrderDto
{
    private Long id;

    @NotNull(message = "Product ID cannot be null")
    private Long productId;

    @NotNull(message = "Order date is required")
    private LocalDate date;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotNull(message = "Status is required")
    private Status status;

    @NotBlank(message = "Supplier cannot be blank")
    @Size(min = 2, max = 100, message = "Supplier name must be between 2 and 100 characters")
    private String supplier;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than 0")
    private BigDecimal price;
}
