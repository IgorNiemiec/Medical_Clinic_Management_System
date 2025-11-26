package org.example.medical_clinic_management_system.dto.medicalService;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicalServiceRequestDto
{
    @Size(max = 100)
    private String title;
    private String description;
    @DecimalMin(value = "0.00")
    private BigDecimal price;

}
