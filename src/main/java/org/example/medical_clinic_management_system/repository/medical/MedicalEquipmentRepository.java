package org.example.medical_clinic_management_system.repository.medical;

import org.example.medical_clinic_management_system.model.medical.MedicalEquipment;
import org.example.medical_clinic_management_system.model.medical.MedicalEquipment.Status;
import org.example.medical_clinic_management_system.model.medical.MedicalEquipment.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MedicalEquipmentRepository extends JpaRepository<MedicalEquipment, Long>
{

    List<MedicalEquipment> findByNameContainingIgnoreCase(String name);

    List<MedicalEquipment> findByType(Type type);

    List<MedicalEquipment> findByStatus(Status status);

    List<MedicalEquipment> findByLocationContainingIgnoreCase(String location);

    List<MedicalEquipment> findByTypeAndStatus(Type type, Status status);

    List<MedicalEquipment> findByLocationContainingIgnoreCaseAndStatus(String location, Status status);

     List<MedicalEquipment> findByQuantityGreaterThan(Integer minQuantity);


}
