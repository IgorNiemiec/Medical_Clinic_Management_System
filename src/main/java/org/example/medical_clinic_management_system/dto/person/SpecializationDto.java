package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class SpecializationDto {
    private Long id;

    @NotBlank(message = "{validation.specialization.name.notBlank}")
    @Size(min = 2, max = 100, message = "{validation.specialization.name.size}")
    private String name;

    @Size(max = 500, message = "{validation.specialization.description.size}")
    private String description;
}
