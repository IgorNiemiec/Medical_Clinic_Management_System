package org.example.medical_clinic_management_system.repository.medical;

import org.example.medical_clinic_management_system.model.medical.MedicinalProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface MedicinalProductRepository extends JpaRepository<MedicinalProduct, Long>
{

    Optional<MedicinalProduct> findByName(String name);

    List<MedicinalProduct> findByNameContainingIgnoreCase(String keyword);

    List<MedicinalProduct> findByManufacturerContainingIgnoreCase(String manufacturer);

    List<MedicinalProduct> findByFormIgnoreCase(String form);

    List<MedicinalProduct> findByCategoryContainingIgnoreCase(String category);

    List<MedicinalProduct> findByPriceLessThan(BigDecimal maxPrice);

    List<MedicinalProduct> findByQuantityGreaterThan(Integer minQuantity);

    List<MedicinalProduct> findByNameContainingIgnoreCaseAndManufacturerContainingIgnoreCase(String name, String manufacturer);

}
