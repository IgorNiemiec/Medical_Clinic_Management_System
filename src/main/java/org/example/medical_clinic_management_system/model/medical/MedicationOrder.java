package org.example.medical_clinic_management_system.model.medical;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "medication_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicationOrder
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medicinal_product_id", nullable = false)
    private MedicinalProduct product;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(length = 50, nullable = false)
    private String supplier;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    public enum Status {
        ORDERED,
        DELIVERED,
        CANCELLED
    }
}
