package org.example.medical_clinic_management_system.dto.visit;

import lombok.Builder;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.MedicalNote.NoteType;

import java.time.LocalDate;

@Data
@Builder
public class MedicalNoteDto
{
    private Long id;
    private Long appointmentId;
    private String content;
    private LocalDate date;
    private NoteType type;
}
