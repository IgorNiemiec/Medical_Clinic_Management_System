package org.example.medical_clinic_management_system.dto.person;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;

@Data
public class MedicalStaffRequestDto
{

    @NotNull(message = "{validation.medicalStaff.employeeId.notNull}")
    @Min(value = 1, message = "{validation.medicalStaff.employeeId.min}")
    private Long employeeId;

    @NotNull(message = "{validation.medicalStaff.profession.notNull}")
    private MedicalStaff.Profession profession;

    @NotBlank(message = "{validation.medicalStaff.licenseNumber.notBlank}")
    @Size(min = 5, max = 50, message = "{validation.medicalStaff.licenseNumber.size}")
    private String licenseNumber;

    @NotNull(message = "{validation.medicalStaff.availability.notNull}")
    private boolean availability;

}
