package org.example.medical_clinic_management_system.dto.payment;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.example.medical_clinic_management_system.model.payment.Payment.PaymentMethod;
import org.example.medical_clinic_management_system.model.payment.Payment.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDto {
    private Long id;

    @NotNull(message = "{validation.payment.appointmentId.notNull}")
    private Long appointmentId;

    @NotNull(message = "{validation.payment.amount.notNull}")
    @DecimalMin(value = "0.01", inclusive = true, message = "{validation.payment.amount.min}")
    private BigDecimal amount;

    @NotNull(message = "{validation.payment.date.notNull}")
    private LocalDateTime date;

    @NotNull(message = "{validation.payment.paymentMethod.notNull}")
    private PaymentMethod paymentMethod;

    @NotNull(message = "{validation.payment.status.notNull}")
    private Status status;

    @NotBlank(message = "{validation.payment.transactionNumber.notBlank}")
    @Size(min = 5, max = 50, message = "{validation.payment.transactionNumber.size}")
    private String transactionNumber;
}
