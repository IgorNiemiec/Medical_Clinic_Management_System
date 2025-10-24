package org.example.medical_clinic_management_system.repository.visit;

import org.example.medical_clinic_management_system.model.visit.MedicalNote;
import org.example.medical_clinic_management_system.model.visit.MedicalNote.NoteType;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MedicalNoteRepository extends JpaRepository<MedicalNote, Long>
{

    List<MedicalNote> findByAppointment(Appointment appointment);

    List<MedicalNote> findByType(NoteType type);

    List<MedicalNote> findByDateAfter(LocalDate date);

    List<MedicalNote> findByDateBefore(LocalDate date);

    List<MedicalNote> findByContentContainingIgnoreCase(String keyword);

    List<MedicalNote> findByTypeAndDate(NoteType type, LocalDate date);

}
