package org.example.medical_clinic_management_system.repository.record;

import org.example.medical_clinic_management_system.model.record.DiseaseCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseCourseRepository extends JpaRepository<DiseaseCourse, Long>
{

    List<DiseaseCourse> findByPatientCardIdOrderByDiagnosisDateDesc(Long patientCardId);





}
