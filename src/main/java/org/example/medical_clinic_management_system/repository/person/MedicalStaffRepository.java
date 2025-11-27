package org.example.medical_clinic_management_system.repository.person;

import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.MedicalStaff.Profession;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long>
{

    Optional<MedicalStaff> findByLicenseNumber(String licenseNumber);

    boolean existsByLicenseNumberAndIdNot(String licenseNumber, Long id);

    Optional<MedicalStaff> findByEmployeeId(Long employeeId);

}
