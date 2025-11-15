package org.example.medical_clinic_management_system.dto.payment;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.example.medical_clinic_management_system.model.payment.Payment.PaymentMethod;
import org.example.medical_clinic_management_system.model.payment.Payment.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentDto
{
    private Long id;

    @NotNull(message = "Appointment ID cannot be null")
    private Long appointmentId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Payment date is required")
    private LocalDateTime date;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;

    @NotNull(message = "Status is required")
    private Status status;

    @NotBlank(message = "Transaction number cannot be blank")
    @Size(min = 5, max = 50, message = "Transaction number must be between 5 and 50 characters")
    private String transactionNumber;
}
