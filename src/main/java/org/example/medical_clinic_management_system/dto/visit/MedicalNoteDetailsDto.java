package org.example.medical_clinic_management_system.dto.visit;

import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.MedicalNote;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MedicalNoteDetailsDto
{
    private Long id;

    private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalDateTime appointmentTime;

    private Long medicalStaffId;
    private String medicalStaffFullName;
    private String medicalStaffProfession;

    private MedicalNote.MedicalNoteType noteType;
    private String content;
    private LocalDate date;
    private LocalDateTime createdAt;
}
