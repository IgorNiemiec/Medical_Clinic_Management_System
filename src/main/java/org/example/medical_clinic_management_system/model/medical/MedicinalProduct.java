package org.example.medical_clinic_management_system.model.medical;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "medicinal_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicinalProduct
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String composition;

    @Column(length = 100, nullable = false)
    private String manufacturer;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(length = 50, nullable = false)
    private String form;

    @Column(length = 100, nullable = false)
    private String category;

    @Column(nullable = false)
    private Integer quantity;

}
