package org.example.medical_clinic_management_system.dto.payment;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceRequestDto
{
    @NotNull(message = "{validation.invoice.patientId.notNull}")
    @Min(value = 1, message = "{validation.invoice.patientId.min}")
    private Long patientId;

    @NotEmpty(message = "{validation.invoice.appointmentMedicalServiceIds.notEmpty}")
    @NotNull(message = "{validation.invoice.appointmentMedicalServiceId.notNull}")
    private List<Long> appointmentMedicalServiceIds;

    @NotBlank(message = "{validation.invoice.payerName.notBlank}")
    @Size(min = 3, max = 255, message = "{validation.invoice.payerName.size}")
    private String payerName;

    @NotBlank(message = "{validation.invoice.payerAddress.notBlank}")
    @Size(min = 5, max = 255, message = "{validation.invoice.payerAddress.size}")
    private String payerAddress;

    @NotBlank(message = "{validation.invoice.payerNip.notBlank}")
    @Pattern(regexp = "^\\d{10}$", message = "{validation.invoice.payerNip.pattern}")
    private String payerNip;

    @NotNull(message = "{validation.invoice.dueDate.notNull}")
    @FutureOrPresent(message = "{validation.invoice.dueDate.futureOrPresent}")
    private LocalDateTime dueDate;
}
