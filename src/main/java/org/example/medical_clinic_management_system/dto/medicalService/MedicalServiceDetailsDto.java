package org.example.medical_clinic_management_system.dto.medicalService;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicalServiceDetailsDto
{
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
}
