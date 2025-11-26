package org.example.medical_clinic_management_system.repository.person;

import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.Receptionist;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Long>
{

    Optional<Receptionist> findByEmployee(Employee employee);



}
