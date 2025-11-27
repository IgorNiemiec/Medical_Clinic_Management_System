package org.example.medical_clinic_management_system.dto.medicalService;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicalServiceRequestDto
{
    @NotBlank(message = "{validation.medicalService.title.notBlank}")
    @Size(max = 100, message = "{validation.medicalService.title.size}")
    private String title;

    @Size(max = 1000, message = "{validation.medicalService.description.size}")
    private String description;

    @NotNull(message = "{validation.medicalService.price.notNull}")
    @DecimalMin(value = "0.00", message = "{validation.medicalService.price.min}")
    private BigDecimal price;

}
