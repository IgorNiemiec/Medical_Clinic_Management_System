package org.example.medical_clinic_management_system.dto.medicalService;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AppointmentMedicalServiceDetailsDto
{
    private Long id;
    private Long appointmentId;
    private Long medicalServiceId;
    private Long invoiceId;
    private String serviceTitle;
    private BigDecimal priceAtTime;
    private Integer quantity;
    private BigDecimal totalCost;
    private String billingNote;
}
