package org.example.medical_clinic_management_system.repository.visit;

import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExaminationRoomRepository extends JpaRepository<ExaminationRoom, Long>
{

    Optional<ExaminationRoom> findByNumber(String number);

    List<ExaminationRoom> findByLocationContainingIgnoreCase(String location);

    List<ExaminationRoom> findByStatus(Status status);

    List<ExaminationRoom> findByPurposeContainingIgnoreCase(String keyword);

    List<ExaminationRoom> findByStatusAndPurposeContainingIgnoreCase(Status status, String keyword);

}
