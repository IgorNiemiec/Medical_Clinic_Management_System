package org.example.medical_clinic_management_system.dto.medical;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicinalProductDto
{
    private Long id;
    private String name;
    private String composition;
    private String manufacturer;
    private BigDecimal price;
    private String form;
    private String category;
    private Integer quantity;
}
