package org.example.medical_clinic_management_system.dto.visit;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.MedicalNote.NoteType;

import java.time.LocalDate;

@Data
@Builder
public class MedicalNoteDto
{
    private Long id;

    @NotNull(message = "Appointment ID cannot be null")
    private Long appointmentId;

    @NotBlank(message = "Note content cannot be blank")
    @Size(min = 5, max = 2000, message = "Note content must be between 5 and 2000 characters")
    private String content;

    @NotNull(message = "Note date is required")
    @PastOrPresent(message = "Note date cannot be in the future")
    private LocalDate date;

    @NotNull(message = "Note type is required")
    private NoteType type;
}
