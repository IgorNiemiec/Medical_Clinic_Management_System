package org.example.medical_clinic_management_system.model.visit;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_staff_id", nullable = false)
    private MedicalStaff medicalStaff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examination_room_id", nullable = false)
    private ExaminationRoom examinationRoom;


    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AppointmentType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AppointmentStatus status;

    @Column(columnDefinition = "TEXT")
    private String description;


    public enum AppointmentType
    {
        CONSULTATION,
        FOLLOW_UP,
        PROCEDURE,
        TELEMEDICINE
    }

    public enum AppointmentStatus
    {
        SCHEDULED,
        CONFIRMED,
        COMPLETED,
        CANCELLED_BY_PATIENT,
        CANCELLED_BY_STAFF,
        NO_SHOW
    }

}
