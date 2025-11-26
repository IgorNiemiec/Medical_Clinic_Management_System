package org.example.medical_clinic_management_system.repository.medicalService;

import org.example.medical_clinic_management_system.model.medicalService.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long>
{

    boolean existsByTitleIgnoreCase(String title);

    Optional<MedicalService> findByTitleIgnoreCase(String title);

    List<MedicalService> findByTitleContainingIgnoreCaseOrderByTitleAsc(String titleFragment);

}
