package org.example.medical_clinic_management_system.dto.medical;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class DiseaseDto
{
    private Long id;

    @NotBlank(message = "Disease name cannot be blank")
    @Size(min = 2, max = 100, message = "Disease name must be between 2 and 100 characters")
    private String name;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @Size(min = 3, max = 10, message = "ICD code must be between 3 and 10 characters")
    private String icdCode;
}
