package org.example.medical_clinic_management_system.model.person;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "medical_staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalStaff
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private boolean availability;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Profession profession;

    @Column(name = "license_number", unique = true, nullable = false)
    private String licenseNumber;

    public enum Profession {
        DOCTOR,
        NURSE,
        TECHNICIAN,
        OTHER
    }


}
