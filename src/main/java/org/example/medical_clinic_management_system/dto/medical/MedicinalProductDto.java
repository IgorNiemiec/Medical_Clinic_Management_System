package org.example.medical_clinic_management_system.dto.medical;

import lombok.Data;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Data
public class MedicinalProductDto
{
    private Long id;

    @NotBlank(message = "Product name cannot be blank")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Composition cannot be blank")
    @Size(max = 500, message = "Composition cannot exceed 500 characters")
    private String composition;

    @NotBlank(message = "Manufacturer cannot be blank")
    @Size(min = 2, max = 100, message = "Manufacturer name must be between 2 and 100 characters")
    private String manufacturer;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotBlank(message = "Form cannot be blank")
    @Size(max = 50, message = "Form cannot exceed 50 characters")
    private String form;

    @NotBlank(message = "Category cannot be blank")
    @Size(max = 50, message = "Category cannot exceed 50 characters")
    private String category;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;
}
