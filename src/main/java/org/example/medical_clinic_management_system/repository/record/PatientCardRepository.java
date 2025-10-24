package org.example.medical_clinic_management_system.repository.record;

import org.example.medical_clinic_management_system.model.record.PatientCard;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientCardRepository extends JpaRepository<PatientCard, Long>
{

    Optional<PatientCard> findByPatient(Patient patient);

    List<PatientCard> findByCreatedAtAfter(LocalDate date);

    List<PatientCard> findByCreatedAtBefore(LocalDate date);

    List<PatientCard> findByMedicalHistoryContainingIgnoreCase(String keyword);

    List<PatientCard> findByAllergiesContainingIgnoreCase(String keyword);

}
