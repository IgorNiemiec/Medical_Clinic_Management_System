package org.example.medical_clinic_management_system.model.visit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_note")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalNote
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_staff_id", nullable = false)
    private MedicalStaff medicalStaff;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "note_type", nullable = false, length = 50)
    private MedicalNoteType noteType;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum MedicalNoteType
    {
        EXAMINATION_REPORT,
        DIAGNOSIS_SUMMARY,
        TREATMENT_PLAN,
        PROCEDURE_NOTE,
        REFERRAL_NOTE,
        OTHER
    }


}
