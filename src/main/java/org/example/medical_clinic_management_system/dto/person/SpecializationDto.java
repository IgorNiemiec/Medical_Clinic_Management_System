package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class SpecializationDto
{
    private Long id;

    @NotBlank(message = "Specialization name cannot be blank")
    @Size(min = 2, max = 100, message = "Specialization name must be between 2 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
}
