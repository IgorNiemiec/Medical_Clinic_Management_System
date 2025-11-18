package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.example.medical_clinic_management_system.model.person.MedicalStaff.Profession;

import java.time.LocalDate;

@Data
public class MedicalStaffDto {
    private Long id;

    @NotNull(message = "{validation.medicalStaff.userId.notNull}")
    private Long userId;

    @NotNull(message = "{validation.medicalStaff.employmentDate.notNull}")
    @PastOrPresent(message = "{validation.medicalStaff.employmentDate.pastOrPresent}")
    private LocalDate employmentDate;

    @NotBlank(message = "{validation.medicalStaff.workPhone.notBlank}")
    @Pattern(regexp = "^[0-9\\-+]{9,15}$", message = "{validation.medicalStaff.workPhone.pattern}")
    private String workPhone;

    private boolean available;

    @NotNull(message = "{validation.medicalStaff.profession.notNull}")
    private Profession profession;
}
