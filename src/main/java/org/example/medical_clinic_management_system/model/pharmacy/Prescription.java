package org.example.medical_clinic_management_system.model.pharmacy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Patient;

import java.time.LocalDate;

@Entity
@Table(name = "prescription")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private MedicalStaff doctor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "drug_id", nullable = false)
    private Drug drug;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "dosage_instructions", columnDefinition = "TEXT", nullable = false)
    private String dosageInstructions;

    @Column(nullable = false)
    private Integer quantity;

}
