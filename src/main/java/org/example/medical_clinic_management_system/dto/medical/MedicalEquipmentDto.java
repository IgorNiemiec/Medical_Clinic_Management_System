package org.example.medical_clinic_management_system.dto.medical;

import lombok.Data;
import org.example.medical_clinic_management_system.model.medical.MedicalEquipment.Status;
import org.example.medical_clinic_management_system.model.medical.MedicalEquipment.Type;

@Data
public class MedicalEquipmentDto
{
    private Long id;
    private String name;
    private Type type;
    private Integer quantity;
    private String location;
    private Status status;
}
