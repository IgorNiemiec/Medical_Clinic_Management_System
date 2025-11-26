package org.example.medical_clinic_management_system.dto.payment;

import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import org.example.medical_clinic_management_system.model.payment.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentRequestDto
{
    private Long invoiceId;
    @DecimalMin(value = "0.01")
    private BigDecimal amount;
    private Payment.PaymentMethod paymentMethod;
    private LocalDateTime paymentDateTime;
    private String transactionReference;
}
