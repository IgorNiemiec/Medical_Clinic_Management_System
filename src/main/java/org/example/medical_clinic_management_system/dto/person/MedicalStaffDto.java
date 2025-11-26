package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.example.medical_clinic_management_system.model.person.MedicalStaff.Profession;

import java.time.LocalDate;

@Data
public class MedicalStaffDto {
    private Long id;

    @NotNull(message = "{validation.medicalStaff.employeeId.notNull}")
    private Long employeeId;

    private boolean availability;

    @NotNull(message = "{validation.medicalStaff.profession.notNull}")
    private Profession profession;
}
