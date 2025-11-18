package org.example.medical_clinic_management_system.dto.medical;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DiseaseDto {
    private Long id;

    @NotBlank(message = "{validation.disease.name.notBlank}")
    @Size(min = 2, max = 100, message = "{validation.disease.name.size}")
    private String name;

    @Size(max = 1000, message = "{validation.disease.description.size}")
    private String description;

    @Size(min = 3, max = 10, message = "{validation.disease.icdCode.size}")
    private String icdCode;
}
