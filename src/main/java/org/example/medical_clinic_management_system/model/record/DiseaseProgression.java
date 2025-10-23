package org.example.medical_clinic_management_system.model.record;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.medical.Disease;

import java.time.LocalDate;

@Entity
@Table(name = "disease_progression")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiseaseProgression
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_card_id", nullable = false)
    private PatientCard patientCard;

    @ManyToOne(optional = false)
    @JoinColumn(name = "disease_id", nullable = false)
    private Disease disease;

    @Column(name = "diagnosis_date", nullable = false)
    private LocalDate diagnosisDate;

    @Column(columnDefinition = "TEXT")
    private String symptoms;

    @Column(columnDefinition = "TEXT")
    private String treatment;

}
