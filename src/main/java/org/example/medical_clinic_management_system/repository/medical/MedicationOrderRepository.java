package org.example.medical_clinic_management_system.repository.medical;
import org.example.medical_clinic_management_system.model.medical.MedicationOrder;
import org.example.medical_clinic_management_system.model.medical.MedicationOrder.Status;
import org.example.medical_clinic_management_system.model.medical.MedicinalProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface MedicationOrderRepository extends JpaRepository<MedicationOrder, Long>
{

    List<MedicationOrderRepository> findByProduct(MedicinalProduct product);

    List<MedicationOrderRepository> findByStatus(Status status);

    List<MedicationOrderRepository> findBySupplierIgnoreCase(String supplier);

    List<MedicationOrderRepository> findByDateBetween(LocalDate start, LocalDate end);

    List<MedicationOrderRepository> findByProductAndStatus(MedicinalProduct product, Status status);

    List<MedicationOrderRepository> findByPriceGreaterThan(BigDecimal minPrice);

    List<MedicationOrderRepository> findBySupplierIgnoreCaseAndStatus(String supplier, Status status);


}
