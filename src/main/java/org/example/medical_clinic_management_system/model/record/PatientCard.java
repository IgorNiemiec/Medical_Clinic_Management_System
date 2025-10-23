package org.example.medical_clinic_management_system.model.record;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.person.Patient;

import java.time.LocalDate;

@Entity
@Table(name = "patient_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientCard
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false, unique = true)
    private Patient patient;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "medical_history", columnDefinition = "TEXT")
    private String medicalHistory;

    @Column(columnDefinition = "TEXT")
    private String allergies;


}
