package org.example.medical_clinic_management_system.dto.visit;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.MedicalNote;

import java.time.LocalDate;

@Data
public class MedicalNoteRequestDto
{

    @NotNull(message = "{validation.medicalNote.appointmentId.notNull}")
    @Min(value = 1, message = "{validation.medicalNote.appointmentId.min}")
    private Long appointmentId;

    @NotNull(message = "{validation.medicalNote.medicalStaffId.notNull}")
    @Min(value = 1, message = "{validation.medicalNote.medicalStaffId.min}")
    private Long medicalStaffId;

    @NotBlank(message = "{validation.medicalNote.content.notBlank}")
    @Size(min = 10, max = 5000, message = "{validation.medicalNote.content.size}")
    private String content;

    @NotNull(message = "{validation.medicalNote.date.notNull}")
    @FutureOrPresent(message = "{validation.medicalNote.date.futureOrPresent}")
    private LocalDate date;

    @NotNull(message = "{validation.medicalNote.noteType.notNull}")
    private MedicalNote.MedicalNoteType noteType;

}
