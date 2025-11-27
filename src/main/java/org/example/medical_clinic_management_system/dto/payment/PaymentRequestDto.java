package org.example.medical_clinic_management_system.dto.payment;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.medical_clinic_management_system.model.payment.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentRequestDto
{
    @NotNull(message = "{validation.payment.invoiceId.notNull}")
    @Min(value = 1, message = "{validation.payment.invoiceId.min}")
    private Long invoiceId;

    @NotNull(message = "{validation.payment.amount.notNull}")
    @DecimalMin(value = "0.01", message = "{validation.payment.amount.min}")
    private BigDecimal amount;

    @NotNull(message = "{validation.payment.paymentMethod.notNull}")
    private Payment.PaymentMethod paymentMethod;

    @NotNull(message = "{validation.payment.paymentDateTime.notNull}")
    @PastOrPresent(message = "{validation.payment.paymentDateTime.pastOrPresent}")
    private LocalDateTime paymentDateTime;

    @NotBlank(message = "{validation.payment.transactionReference.notBlank}")
    @Size(min = 5, max = 100, message = "{validation.payment.transactionReference.size}")
    private String transactionReference;
}
