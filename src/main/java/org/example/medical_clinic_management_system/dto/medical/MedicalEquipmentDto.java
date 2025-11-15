package org.example.medical_clinic_management_system.dto.medical;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.medical_clinic_management_system.model.medical.MedicalEquipment.Status;
import org.example.medical_clinic_management_system.model.medical.MedicalEquipment.Type;

@Data
public class MedicalEquipmentDto
{
    private Long id;

    @NotBlank(message = "Equipment name cannot be blank")
    @Size(min = 2, max = 100, message = "Equipment name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Equipment type is required")
    private Type type;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    @NotBlank(message = "Location cannot be blank")
    @Size(max = 255, message = "Location cannot exceed 255 characters")
    private String location;

    @NotNull(message = "Status is required")
    private Status status;
}
