package org.example.medical_clinic_management_system.dto.payment;

import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceRequestDto
{
    private Long patientId;

    private List<Long> appointmentMedicalServiceIds;

    private String payerName;

    private String payerAddress;

    private String payerNip;

    @FutureOrPresent
    private LocalDateTime dueDate;
}
