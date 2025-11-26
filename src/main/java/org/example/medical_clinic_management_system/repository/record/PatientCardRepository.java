package org.example.medical_clinic_management_system.repository.record;

import org.example.medical_clinic_management_system.model.record.PatientCard;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientCardRepository extends JpaRepository<PatientCard, Long>
{

    Optional<PatientCard> findByPatientId(Long patientId);

    boolean existsByPatientId(Long patientId);

}
