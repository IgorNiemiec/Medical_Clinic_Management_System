package org.example.medical_clinic_management_system.model.visit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.person.Receptionist;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Patient;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "receptionist_id", nullable = false)
    private Receptionist receptionist;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medicalStaff_id", nullable = false)
    private MedicalStaff medicalStaff;

    @ManyToOne(optional = false)
    @JoinColumn(name = "examinationRoom_id", nullable = false)
    private ExaminationRoom room;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime time;

    @Column(length = 100, nullable = false)
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private String description;

    public enum Status {
        SCHEDULED,
        COMPLETED,
        CANCELLED
    }

}
