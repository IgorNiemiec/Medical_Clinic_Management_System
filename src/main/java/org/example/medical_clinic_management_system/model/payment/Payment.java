package org.example.medical_clinic_management_system.model.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.example.medical_clinic_management_system.repository.payment.InvoiceRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false, length = 50)
    private PaymentMethod paymentMethod;

    @Column(name = "payment_date_time", nullable = false)
    private LocalDateTime paymentDateTime;

    @Column(name = "transaction_reference", length = 100)
    private String transactionReference;



    public enum PaymentMethod {
        CASH,
        CARD,
        BANK_TRANSFER,
        BLIK,
        INSURANCE
    }


}
