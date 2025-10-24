package org.example.medical_clinic_management_system.dto.medical;

import lombok.Data;
import org.example.medical_clinic_management_system.model.medical.MedicationOrder.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MedicationOrderDto
{
    private Long id;
    private Long productId;
    private LocalDate date;
    private Integer quantity;
    private Status status;
    private String supplier;
    private BigDecimal price;
}
