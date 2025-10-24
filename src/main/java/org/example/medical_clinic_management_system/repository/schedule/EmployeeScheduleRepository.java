package org.example.medical_clinic_management_system.repository.schedule;

import org.example.medical_clinic_management_system.model.schedule.EmployeeSchedule;
import org.example.medical_clinic_management_system.model.schedule.EmployeeSchedule.ScheduleType;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeScheduleRepository extends JpaRepository<EmployeeSchedule, Long>
{

    List<EmployeeSchedule> findByEmployee(MedicalStaff employee);

    List<EmployeeSchedule> findByEmployeeAndDate(MedicalStaff employee, LocalDate date);

    List<EmployeeSchedule> findByType(ScheduleType type);

    List<EmployeeSchedule> findByEmployeeAndType(MedicalStaff employee, ScheduleType type);

    List<EmployeeSchedule> findByDateBetween(LocalDate start, LocalDate end);

    List<EmployeeSchedule> findByTypeAndDateBetween(ScheduleType type, LocalDate start, LocalDate end);

}
