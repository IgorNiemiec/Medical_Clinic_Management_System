package org.example.medical_clinic_management_system.dto.visit;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.MedicalNote;

import java.time.LocalDate;

@Data
@Builder
public class MedicalNoteDto {
    private Long id;

    @NotNull(message = "{validation.medicalNote.appointmentId.notNull}")
    private Long appointmentId;

    @NotBlank(message = "{validation.medicalNote.content.notBlank}")
    @Size(min = 5, max = 2000, message = "{validation.medicalNote.content.size}")
    private String content;

    @NotNull(message = "{validation.medicalNote.date.notNull}")
    @PastOrPresent(message = "{validation.medicalNote.date.pastOrPresent}")
    private LocalDate date;

    @NotNull(message = "{validation.medicalNote.type.notNull}")
    private NoteType type;

    public enum NoteType
    {
        SHORT,
        LONG
    }

}
