package org.example.medical_clinic_management_system.dto.medicalService;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AppointmentMedicalServiceRequestDto
{

    private Long appointmentId;

    private Long medicalServiceId;

    private Long invoiceId;

    @Min(value = 1)
    private Integer quantity;

    @DecimalMin(value = "0.00")
    private BigDecimal unitPrice;

    private String billingNote;

}
