package org.example.medical_clinic_management_system.model.schedule;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Receptionist;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_staff_id", nullable = false)
    private MedicalStaff medicalStaff;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private Receptionist createdBy;

    @Column(name = "schedule_date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ScheduleType type;

    public enum ScheduleType {
        SHIFT,
        ON_CALL,
        VACATION,
        TRAINING
    }

}
