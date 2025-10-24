package org.example.medical_clinic_management_system.repository.medical;

import org.example.medical_clinic_management_system.model.medical.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiseaseRepository extends JpaRepository<Disease, Long>
{

    Optional<Disease> findByName(String name);

    Optional<Disease> findByIcdCode(String icdCode);

    List<Disease> findByNameContainingIgnoreCase(String keyword);

    List<Disease> findByDescriptionContainingIgnoreCase(String keyword);

}
