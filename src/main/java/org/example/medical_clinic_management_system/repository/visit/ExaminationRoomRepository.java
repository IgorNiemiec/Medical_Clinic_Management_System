package org.example.medical_clinic_management_system.repository.visit;

import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExaminationRoomRepository extends JpaRepository<ExaminationRoom, Long>
{

    Optional<ExaminationRoom> findByNumber(String number);

    List<ExaminationRoom> findByStatus(ExaminationRoom.ExaminationRoomStatus status);
}
