package org.example.medical_clinic_management_system.repository.record;

import org.example.medical_clinic_management_system.model.record.ICDCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICDCodeRepository extends JpaRepository<ICDCode, Long>
{
    Optional<ICDCode> findByCode(String code);

    List<ICDCode> findByNamePlContainingIgnoreCaseOrNameEnContainingIgnoreCase(String namePl, String nameEn);

    boolean existsByCode(String code);
}
