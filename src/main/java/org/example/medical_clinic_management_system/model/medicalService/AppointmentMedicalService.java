package org.example.medical_clinic_management_system.model.medicalService;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.payment.Invoice;
import org.example.medical_clinic_management_system.model.visit.Appointment;

import java.math.BigDecimal;

@Entity
@Table(name = "appointment_medical_service")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentMedicalService
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_service_id", nullable = false)
    private MedicalService medicalService;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @Column(name = "price_at_time", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceAtTime;

    @Column(nullable = false)
    private Integer quantity;

    @Column(columnDefinition = "TEXT")
    private String billingNote;

    @Column(name = "service_title_snapshot", nullable = false, length = 100)
    private String serviceTitleSnapshot;

    @Transient
    public BigDecimal getTotalCost() {
        if (priceAtTime == null || quantity == null) {
            return BigDecimal.ZERO;
        }
        return priceAtTime.multiply(new BigDecimal(quantity));
    }





}
