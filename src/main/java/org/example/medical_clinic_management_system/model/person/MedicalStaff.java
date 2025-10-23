package org.example.medical_clinic_management_system.model.person;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "medical_staff")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalStaff
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "employment_date")
    private LocalDate employmentDate;

    @Column(name = "work_phone", length = 12, unique = true)
    private String workPhone;

    @Column(nullable = false)
    private boolean available;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Profession profession;

    public enum Profession {
        DOCTOR,
        NURSE,
        TECHNICIAN,
        OTHER
    }


}
