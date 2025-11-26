package org.example.medical_clinic_management_system.model.record;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;

import java.time.LocalDateTime;

@Entity
@Table(name = "disease_course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiseaseCourse
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_card_id", nullable = false)
    private PatientCard patientCard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "icd_code_id", nullable = false)
    private ICDCode icdCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_staff_id", nullable = false)
    private MedicalStaff medicalStaff;

    @Column(nullable = false)
    private LocalDateTime diagnosisDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DiseaseStatus status;



    public enum DiseaseStatus
    {
        ACUTE,
        CHRONIC,
        ON_TREATMENT,
        RECOVERY,
        CURED,
        WORSENING,
    }


    @PrePersist
    protected void onCreate() {
        if (diagnosisDate == null) {
            diagnosisDate = LocalDateTime.now();
        }

        if (this.patientCard != null) {
            this.patientCard.setLastUpdate(LocalDateTime.now());
        }
    }

    public void setPatientCard(PatientCard patientCard) {
        this.patientCard = patientCard;
        if (patientCard != null) {
            patientCard.setLastUpdate(LocalDateTime.now());
        }
    }



}
