package org.example.medical_clinic_management_system.dto.pharmacy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DrugRequestDto
{

    @NotBlank(message = "{validation.drug.productName.notBlank}")
    @Size(min = 2, max = 255, message = "{validation.drug.productName.size}")
    private String productName;

    @NotBlank(message = "{validation.drug.commonName.notBlank}")
    @Size(min = 2, max = 255, message = "{validation.drug.commonName.size}")
    private String commonName;

    @NotBlank(message = "{validation.drug.form.notBlank}")
    @Size(min = 2, max = 100, message = "{validation.drug.form.size}")
    private String form;

    @NotBlank(message = "{validation.drug.atcCode.notBlank}")
    @Pattern(regexp = "^[A-Z0-9]{5,7}$", message = "{validation.drug.atcCode.pattern}")
    private String atcCode;

    @NotBlank(message = "{validation.drug.gtinNumber.notBlank}")
    @Pattern(regexp = "^\\d{14}$", message = "{validation.drug.gtinNumber.pattern}")
    private String gtinNumber;

    private boolean isActive = true;

}
