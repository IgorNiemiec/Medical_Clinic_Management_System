package org.example.medical_clinic_management_system.dto.payment;

import lombok.Data;
import org.example.medical_clinic_management_system.model.payment.Payment.PaymentMethod;
import org.example.medical_clinic_management_system.model.payment.Payment.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDto
{
    private Long id;
    private Long appointmentId;
    private BigDecimal amount;
    private LocalDateTime date;
    private PaymentMethod paymentMethod;
    private Status status;
    private String transactionNumber;
}
