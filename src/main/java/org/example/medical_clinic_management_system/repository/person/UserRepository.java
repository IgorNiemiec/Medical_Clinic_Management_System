package org.example.medical_clinic_management_system.repository.person;

import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.model.person.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{

    Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);

    List<User> findByRegisterDateAfter(LocalDateTime date);

    List<User> findByLastLoginAfter(LocalDateTime date);

    List<User> findBySurnameContainingIgnoreCase(String surname);

    List<User> findByFirstNameAndRole(String firstName, Role role);


}
