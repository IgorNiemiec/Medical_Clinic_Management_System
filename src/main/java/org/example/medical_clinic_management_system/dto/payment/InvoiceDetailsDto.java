package org.example.medical_clinic_management_system.dto.payment;

import lombok.Data;
import org.example.medical_clinic_management_system.dto.medicalService.AppointmentMedicalServiceDetailsDto;
import org.example.medical_clinic_management_system.model.payment.Invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDetailsDto
{
    private Long id;
    private String invoiceNumber;
    private Long patientId;

    private LocalDateTime issueDate;
    private LocalDateTime dueDate;
    private Invoice.InvoiceStatus status;

    private BigDecimal totalNet;
    private BigDecimal totalGross;
    private BigDecimal totalPaid;
    private BigDecimal outstandingBalance;

    private String payerName;
    private String payerAddress;
    private String payerNip;

    private List<AppointmentMedicalServiceDetailsDto> items;

}
