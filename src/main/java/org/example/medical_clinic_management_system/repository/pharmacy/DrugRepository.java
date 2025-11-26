package org.example.medical_clinic_management_system.repository.pharmacy;

import org.example.medical_clinic_management_system.model.pharmacy.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrugRepository extends JpaRepository<Drug, Long>
{

    Optional<Drug> findByProductNameIgnoreCase(String productName);

    Optional<Drug> findByAtcCode(String atcCode);
    Optional<Drug> findByGtinNumber(String gtinNumber);

}
