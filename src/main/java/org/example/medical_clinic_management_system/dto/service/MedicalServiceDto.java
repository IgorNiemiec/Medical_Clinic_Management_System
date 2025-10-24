package org.example.medical_clinic_management_system.dto.service;


import lombok.Builder;
import lombok.Data;
import org.example.medical_clinic_management_system.model.service.MedicalService.Category;

import java.math.BigDecimal;

@Data
@Builder
public class MedicalServiceDto
{
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private Integer duration;
    private Category category;
}
