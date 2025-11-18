package org.example.medical_clinic_management_system.dto.medical;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Data
public class MedicinalProductDto
{
    private Long id;

    @NotBlank(message = "{validation.medicinalProduct.name.notBlank}")
    @Size(min = 2, max = 100, message = "{validation.medicinalProduct.name.size}")
    private String name;

    @NotBlank(message = "{validation.medicinalProduct.composition.notBlank}")
    @Size(max = 500, message = "{validation.medicinalProduct.composition.size}")
    private String composition;

    @NotBlank(message = "{validation.medicinalProduct.manufacturer.notBlank}")
    @Size(min = 2, max = 100, message = "{validation.medicinalProduct.manufacturer.size}")
    private String manufacturer;

    @NotNull(message = "{validation.medicinalProduct.price.notNull}")
    @DecimalMin(value = "0.01", inclusive = true, message = "{validation.medicinalProduct.price.min}")
    private BigDecimal price;

    @NotBlank(message = "{validation.medicinalProduct.form.notBlank}")
    @Size(max = 50, message = "{validation.medicinalProduct.form.size}")
    private String form;

    @NotBlank(message = "{validation.medicinalProduct.category.notBlank}")
    @Size(max = 50, message = "{validation.medicinalProduct.category.size}")
    private String category;

    @NotNull(message = "{validation.medicinalProduct.quantity.notNull}")
    @Min(value = 1, message = "{validation.medicinalProduct.quantity.min}")
    private Integer quantity;
}
