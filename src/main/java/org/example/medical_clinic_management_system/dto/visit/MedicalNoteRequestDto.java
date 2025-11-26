package org.example.medical_clinic_management_system.dto.visit;

import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.MedicalNote;

import java.time.LocalDate;

@Data
public class MedicalNoteRequestDto
{

    private Long appointmentId;

    private Long medicalStaffId;

    private String content;

    private LocalDate date;

    private MedicalNote.MedicalNoteType noteType;

}
