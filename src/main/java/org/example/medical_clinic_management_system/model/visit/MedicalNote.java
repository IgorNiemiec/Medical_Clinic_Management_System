package org.example.medical_clinic_management_system.model.visit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NoteType type;

    public enum NoteType {
        DIAGNOSIS,
        RECOMMENDATION,
        OBSERVATION,
        OTHER
    }


}
