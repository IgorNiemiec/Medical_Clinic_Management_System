package org.example.medical_clinic_management_system.repository.pharmacy;

import org.example.medical_clinic_management_system.model.pharmacy.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long>
{

    List<Prescription> findByPatientId(Long patientId);

    List<Prescription> findByDoctorId(Long doctorId);

    List<Prescription> findByExpirationDateBefore(LocalDate date);

}
