package org.example.medical_clinic_management_system.repository.person;

import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.MedicalStaff.Profession;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long>
{

    Optional<MedicalStaff> findByUser(User user);

    Optional<MedicalStaff> findByWorkPhone(String workPhone);

    List<MedicalStaff> findByAvailableTrue();

    List<MedicalStaff> findByProfession(Profession profession);

    List<MedicalStaff> findByProfessionAndAvailableTrue(Profession profession);

    List<MedicalStaff> findByEmploymentDateAfter(LocalDate date);

    List<MedicalStaff> findByEmploymentDateBefore(LocalDate date);


}
