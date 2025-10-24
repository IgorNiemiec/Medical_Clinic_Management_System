package org.example.medical_clinic_management_system.repository.person;

import org.example.medical_clinic_management_system.model.person.Receptionist;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReceptionistRepository extends JpaRepository<Receptionist, Long>
{

    Optional<Receptionist> findByUser(User user);

    Optional<Receptionist> findByWorkPhone(String workPhone);

    List<Receptionist> findByEmploymentDateAfter(LocalDate date);

    List<Receptionist> findByEmploymentDateBefore(LocalDate date);


}
