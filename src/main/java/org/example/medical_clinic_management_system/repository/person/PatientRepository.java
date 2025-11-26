package org.example.medical_clinic_management_system.repository.person;

import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.person.Patient.Gender;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>
{


    Optional<Patient> findByPesel(String pesel);

    boolean existsByPesel(String pesel);

    Optional<Patient> findByUserId(Long userId);

    List<Patient> findByUserFirstNameContainingIgnoreCaseOrUserSurnameContainingIgnoreCase(String searchFragment, String searchFragment2);

    Optional<Patient> findByPhoneNumber(String phoneNumber);

}
