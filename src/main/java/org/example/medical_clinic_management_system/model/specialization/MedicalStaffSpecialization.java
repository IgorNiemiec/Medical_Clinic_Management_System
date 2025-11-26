package org.example.medical_clinic_management_system.model.specialization;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;

import java.time.LocalDate;

@Entity
@Table(name = "medical_staff_specialization")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalStaffSpecialization
{

    @EmbeddedId
    private MedicalStaffSpecializationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("staffId")
    @JoinColumn(name = "staff_id")
    private MedicalStaff medicalStaff;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("specializationId")
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @Column(name = "date_certified", nullable = false)
    private LocalDate dateCertified;



}
