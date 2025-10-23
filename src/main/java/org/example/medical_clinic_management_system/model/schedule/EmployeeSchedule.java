package org.example.medical_clinic_management_system.model.schedule;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;

import java.time.LocalDate;

@Entity
@Table(name = "employee_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeSchedule
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private MedicalStaff employee;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScheduleType type;

    public enum ScheduleType
    {
        SHIFT,
        ON_CALL,
        VACATION,
        TRAINING
    }




}
