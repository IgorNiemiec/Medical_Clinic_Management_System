package org.example.medical_clinic_management_system.dto.medicalService;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AppointmentMedicalServiceRequestDto
{

    @NotNull(message = "{validation.appointmentMedicalService.appointmentId.notNull}")
    @Min(value = 1, message = "{validation.appointmentMedicalService.appointmentId.min}")
    private Long appointmentId;

    @NotNull(message = "{validation.appointmentMedicalService.medicalServiceId.notNull}")
    @Min(value = 1, message = "{validation.appointmentMedicalService.medicalServiceId.min}")
    private Long medicalServiceId;

    private Long invoiceId;

    @NotNull(message = "{validation.appointmentMedicalService.quantity.notNull}")
    @Min(value = 1, message = "{validation.appointmentMedicalService.quantity.min}")
    private Integer quantity;

    @NotNull(message = "{validation.appointmentMedicalService.unitPrice.notNull}")
    @DecimalMin(value = "0.00", message = "{validation.appointmentMedicalService.unitPrice.min}")
    private BigDecimal unitPrice;

    @Size(max = 500, message = "{validation.appointmentMedicalService.billingNote.size}")
    private String billingNote;

}
