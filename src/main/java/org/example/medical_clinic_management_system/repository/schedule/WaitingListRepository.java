package org.example.medical_clinic_management_system.repository.schedule;

import org.example.medical_clinic_management_system.model.schedule.WaitingList;
import org.example.medical_clinic_management_system.model.schedule.WaitingList.Priority;
import org.example.medical_clinic_management_system.model.schedule.WaitingList.Status;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WaitingListRepository extends JpaRepository<WaitingList, Long>
{

    List<WaitingList> findByPatient(Patient patient);

    List<WaitingList> findByStatus(Status status);

    List<WaitingList> findByPriority(Priority priority);

    List<WaitingList> findByAddedAtAfter(LocalDateTime date);

    List<WaitingList> findByAddedAtBefore(LocalDateTime date);

    List<WaitingList> findByPatientAndStatus(Patient patient, Status status);

    List<WaitingList> findByPriorityAndStatus(Priority priority, Status status);

}
