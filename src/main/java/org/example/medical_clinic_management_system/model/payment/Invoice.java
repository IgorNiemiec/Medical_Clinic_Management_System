package org.example.medical_clinic_management_system.model.payment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.medicalService.AppointmentMedicalService;
import org.example.medical_clinic_management_system.model.person.Patient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppointmentMedicalService> items;


    @Column(name = "invoice_number", unique = true, nullable = false, length = 50)
    private String invoiceNumber;

    @Column(name = "issue_date", nullable = false)
    private LocalDateTime issueDate;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private InvoiceStatus status;

    @Column(name = "total_net", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalNet;

    @Column(name = "total_gross", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalGross;

    @Column(name = "payer_name", nullable = false, length = 200)
    private String payerName;

    @Column(name = "payer_address", length = 300)
    private String payerAddress;

    @Column(name = "payer_nip", length = 30)
    private String payerNip;


    public enum InvoiceStatus {
        DRAFT,
        ISSUED,
        PARTIALLY_PAID,
        PAID,
        CANCELED
    }
}
