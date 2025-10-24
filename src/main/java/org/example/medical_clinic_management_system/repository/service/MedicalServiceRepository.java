package org.example.medical_clinic_management_system.repository.service;

import org.example.medical_clinic_management_system.model.service.MedicalService;
import org.example.medical_clinic_management_system.model.service.MedicalService.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long>
{

    Optional<MedicalService> findByName(String name);

    List<MedicalService> findByNameContainingIgnoreCase(String keyword);

    List<MedicalService> findByCategory(Category category);

    List<MedicalService> findByPriceLessThan(BigDecimal maxPrice);

    List<MedicalService> findByDurationGreaterThan(Integer minDuration);

    List<MedicalService> findByDescriptionContainingIgnoreCase(String keyword);

    List<MedicalService> findByCategoryAndPriceGreaterThan(Category category, BigDecimal minPrice);


}
