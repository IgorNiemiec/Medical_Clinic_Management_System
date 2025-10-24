package org.example.medical_clinic_management_system.repository.visit;

import org.example.medical_clinic_management_system.model.visit.Prescription;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long>
{

    List<Prescription> findByPatient(Patient patient);

    List<Prescription> findByDoctor(MedicalStaff doctor);

    List<Prescription> findByAppointment(Appointment appointment);

    List<Prescription> findByIssuedDateAfter(LocalDate date);

    List<Prescription> findByIssuedDateBefore(LocalDate date);

    List<Prescription> findByRecommendationsContainingIgnoreCase(String keyword);

    List<Prescription> findByDoctorAndPatient(MedicalStaff doctor, Patient patient);

}
