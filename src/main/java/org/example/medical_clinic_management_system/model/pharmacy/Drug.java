package org.example.medical_clinic_management_system.model.pharmacy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "drug")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Drug
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "common_name")
    private String commonName;

    @Column(nullable = false)
    private String form;

    @Column(name = "atc_code", unique = true)
    private String atcCode;

    @Column(name = "gtin_number", unique = true)
    private String gtinNumber;

    @OneToMany(mappedBy = "drug", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prescription> prescriptions;


}
