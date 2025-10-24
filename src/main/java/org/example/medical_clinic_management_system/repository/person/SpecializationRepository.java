package org.example.medical_clinic_management_system.repository.person;

import org.example.medical_clinic_management_system.model.person.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpecializationRepository extends JpaRepository<Specialization, Long>
{

    Optional<Specialization> findByName(String name);

    List<Specialization> findByNameContainingIgnoreCase(String keyword);

    List<Specialization> findByDescriptionContainingIgnoreCase(String keyword);

}
