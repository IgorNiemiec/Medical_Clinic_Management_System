package org.example.medical_clinic_management_system.repository.visit;

import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.example.medical_clinic_management_system.model.visit.Appointment.Status;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Receptionist;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{

    List<Appointment> findByPatient(Patient patient);

    List<Appointment> findByMedicalStaff(MedicalStaff staff);

    List<Appointment> findByReceptionist(Receptionist receptionist);

    List<Appointment> findByRoom(ExaminationRoom room);

    List<Appointment> findByStatus(Status status);

    List<Appointment> findByDate(LocalDate date);

    List<Appointment> findByMedicalStaffAndDate(MedicalStaff staff, LocalDate date);

    List<Appointment> findByPatientAndDate(Patient patient, LocalDate date);

    List<Appointment> findByTypeContainingIgnoreCase(String type);

    List<Appointment> findByRoomAndDateAndTime(ExaminationRoom room, LocalDate date, LocalTime time);

}
