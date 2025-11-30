package org.example.medical_clinic_management_system.repository.person;

import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>
{

    Optional<Employee> findByUserId(Long userId);

    boolean existsByServicePhone(String servicePhone);

    List<Employee> findByUserFirstNameContainingIgnoreCaseOrUserSurnameContainingIgnoreCase(String searchFragment, String searchFragment2);

    Optional<Employee> findByUser(User user);



}
