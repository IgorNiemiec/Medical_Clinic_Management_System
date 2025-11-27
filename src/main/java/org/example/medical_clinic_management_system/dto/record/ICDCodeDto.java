package org.example.medical_clinic_management_system.dto.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ICDCodeDto
{
    private Long id;

    @NotBlank(message = "{validation.icdCode.code.notBlank}")
    @Pattern(regexp = "^[A-Z]\\d{2}(\\.[A-Z0-9]{1,4})?$", message = "{validation.icdCode.code.pattern}")
    @Size(min = 3, max = 7, message = "{validation.icdCode.code.size}")
    private String code;

    @NotBlank(message = "{validation.icdCode.namePl.notBlank}")
    @Size(min = 3, max = 500, message = "{validation.icdCode.namePl.size}")
    private String namePl;

    @NotBlank(message = "{validation.icdCode.nameEn.notBlank}")
    @Size(min = 3, max = 500, message = "{validation.icdCode.nameEn.size}")
    private String nameEn;
}
