package org.example.medical_clinic_management_system.repository.record;

import org.example.medical_clinic_management_system.model.medical.Disease;
import org.example.medical_clinic_management_system.model.record.DiseaseProgression;
import org.example.medical_clinic_management_system.model.record.PatientCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface DiseaseProgressionRepository extends JpaRepository<DiseaseProgression, Long>
{

    List<DiseaseProgressionRepository> findByPatientCard(PatientCard patientCard);

    List<DiseaseProgressionRepository> findByDisease(Disease disease);

    List<DiseaseProgressionRepository> findByPatientCardAndDisease(PatientCard patientCard, Disease disease);

    List<DiseaseProgressionRepository> findByDiagnosisDateAfter(LocalDate date);

    List<DiseaseProgressionRepository> findByDiagnosisDateBefore(LocalDate date);

    List<DiseaseProgressionRepository> findBySymptomsContainingIgnoreCase(String keyword);

    List<DiseaseProgressionRepository> findByTreatmentContainingIgnoreCase(String keyword);


}
