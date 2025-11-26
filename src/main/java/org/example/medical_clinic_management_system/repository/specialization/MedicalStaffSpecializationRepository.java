package org.example.medical_clinic_management_system.repository.specialization;

import org.example.medical_clinic_management_system.model.specialization.MedicalStaffSpecialization;
import org.example.medical_clinic_management_system.model.specialization.MedicalStaffSpecializationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalStaffSpecializationRepository extends JpaRepository<MedicalStaffSpecialization, MedicalStaffSpecializationId>
{

    List<MedicalStaffSpecialization> findByMedicalStaffId(Long staffId);

    List<MedicalStaffSpecialization> findBySpecializationId(Long specializationId);
}
