package org.example.medical_clinic_management_system.dto.service;


import lombok.Builder;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.medical_clinic_management_system.model.service.MedicalService.Category;

import java.math.BigDecimal;

@Data
@Builder
public class MedicalServiceDto
{
    private Long id;

    @NotBlank(message = "Service name cannot be blank")
    @Size(min = 2, max = 100, message = "Service name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than 0")
    private BigDecimal price;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    @Max(value = 480, message = "Duration cannot exceed 480 minutes (8 hours)")
    private Integer duration;

    @NotNull(message = "Category is required")
    private Category category;
}
