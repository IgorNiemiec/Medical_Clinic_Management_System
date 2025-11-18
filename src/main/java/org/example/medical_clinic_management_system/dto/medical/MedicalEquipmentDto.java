package org.example.medical_clinic_management_system.dto.medical;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.medical_clinic_management_system.model.medical.MedicalEquipment.Status;
import org.example.medical_clinic_management_system.model.medical.MedicalEquipment.Type;

@Data
public class MedicalEquipmentDto {
    private Long id;

    @NotBlank(message = "{validation.medicalEquipment.name.notBlank}")
    @Size(min = 2, max = 100, message = "{validation.medicalEquipment.name.size}")
    private String name;

    @NotNull(message = "{validation.medicalEquipment.type.notNull}")
    private Type type;

    @NotNull(message = "{validation.medicalEquipment.quantity.notNull}")
    @Min(value = 1, message = "{validation.medicalEquipment.quantity.min}")
    private Integer quantity;

    @NotBlank(message = "{validation.medicalEquipment.location.notBlank}")
    @Size(max = 255, message = "{validation.medicalEquipment.location.size}")
    private String location;

    @NotNull(message = "{validation.medicalEquipment.status.notNull}")
    private Status status;
}
