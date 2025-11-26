package org.example.medical_clinic_management_system.dto.medicalService;

import lombok.Builder;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.medical_clinic_management_system.model.medicalService.MedicalService.Category;

import java.math.BigDecimal;

@Data
@Builder
public class MedicalServiceDto {
    private Long id;

    @NotBlank(message = "{validation.medicalService.name.notBlank}")
    @Size(min = 2, max = 100, message = "{validation.medicalService.name.size}")
    private String name;

    @NotNull(message = "{validation.medicalService.price.notNull}")
    @DecimalMin(value = "0.01", inclusive = true, message = "{validation.medicalService.price.min}")
    private BigDecimal price;

    @Size(max = 1000, message = "{validation.medicalService.description.size}")
    private String description;

    @NotNull(message = "{validation.medicalService.duration.notNull}")
    @Min(value = 1, message = "{validation.medicalService.duration.min}")
    @Max(value = 480, message = "{validation.medicalService.duration.max}")
    private Integer duration;

    @NotNull(message = "{validation.medicalService.category.notNull}")
    private Category category;
}
