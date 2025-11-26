package org.example.medical_clinic_management_system.model.record;


import jakarta.persistence.*;
import lombok.*;
import org.example.medical_clinic_management_system.model.person.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patient_card")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientCard
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false, unique = true)
    private Patient patient;

    @Column(nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();


    @Column(nullable = false)
    private LocalDateTime lastUpdate;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "patientCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DiseaseCourse> diseaseCourses = new HashSet<>();

    public void addDiseaseCourse(DiseaseCourse course) {
        diseaseCourses.add(course);
        course.setPatientCard(this);
    }

    public void removeDiseaseCourse(DiseaseCourse course) {
        diseaseCourses.remove(course);
        course.setPatientCard(null);
    }




}
