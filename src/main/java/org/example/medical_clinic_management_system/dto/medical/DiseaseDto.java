package org.example.medical_clinic_management_system.dto.medical;

import lombok.Data;

@Data
public class DiseaseDto
{
    private Long id;
    private String name;
    private String description;
    private String icdCode;
}
