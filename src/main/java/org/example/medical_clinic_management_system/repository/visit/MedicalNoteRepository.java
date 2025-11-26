package org.example.medical_clinic_management_system.repository.visit;

import org.example.medical_clinic_management_system.model.visit.MedicalNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicalNoteRepository extends JpaRepository<MedicalNote, Long>
{
    List<MedicalNote> findByAppointmentIdOrderByCreatedAtAsc(Long appointmentId);

    List<MedicalNote> findByMedicalStaffIdOrderByDateDesc(Long medicalStaffId);

    List<MedicalNote> findByAppointmentPatientIdOrderByDateDesc(Long patientId);

    List<MedicalNote> findByAppointmentPatientIdAndNoteTypeOrderByDateDesc(Long patientId, MedicalNote.MedicalNoteType noteType);
}
