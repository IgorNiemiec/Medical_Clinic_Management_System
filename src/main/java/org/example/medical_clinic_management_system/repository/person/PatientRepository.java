package org.example.medical_clinic_management_system.repository.person;

import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.person.Patient.Gender;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long>
{

    Optional<Patient> findByUser(User user);

    Optional<Patient> findByPesel(String pesel);

    Optional<Patient> findByPhone(String phone);

    List<Patient> findByGender(Gender gender);

    List<Patient> findByDateOfBirthBefore(LocalDate date);

    List<Patient> findByDateOfBirthAfter(LocalDate date);

    List<Patient> findByAddressContainingIgnoreCase(String keyword);


}
