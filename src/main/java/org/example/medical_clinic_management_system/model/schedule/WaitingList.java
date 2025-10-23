package org.example.medical_clinic_management_system.model.schedule;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.person.Patient;

import java.time.LocalDateTime;

@Entity
@Table(name = "waiting_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaitingList
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Priority {
        NORMAL,
        HIGH
    }

    public enum Status {
        WAITING,
        ASSIGNED,
        REMOVED
    }

}
