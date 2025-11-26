package org.example.medical_clinic_management_system.dto.payment;

import lombok.Data;
import org.example.medical_clinic_management_system.model.payment.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDetailsDto
{
    private Long id;
    private Long invoiceId;
    private BigDecimal amount;
    private Payment.PaymentMethod paymentMethod;
    private LocalDateTime paymentDateTime;
    private String transactionReference;
}
