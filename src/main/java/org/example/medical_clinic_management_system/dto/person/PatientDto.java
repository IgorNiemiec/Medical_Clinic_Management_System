package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.example.medical_clinic_management_system.model.person.Patient.Gender;

import java.time.LocalDate;

@Data
public class PatientDto {
    private Long id;

    @NotNull(message = "{validation.patient.userId.notNull}")
    private Long userId;

    @NotNull(message = "{validation.patient.dateOfBirth.notNull}")
    @Past(message = "{validation.patient.dateOfBirth.past}")
    private LocalDate dateOfBirth;

    @NotBlank(message = "{validation.patient.address.notBlank}")
    @Size(min = 5, max = 255, message = "{validation.patient.address.size}")
    private String address;

    @NotBlank(message = "{validation.patient.phone.notBlank}")
    @Pattern(regexp = "^[0-9\\-+]{9,15}$", message = "{validation.patient.phone.pattern}")
    private String phone;

    @NotBlank(message = "{validation.patient.pesel.notBlank}")
    @Pattern(regexp = "^[0-9]{11}$", message = "{validation.patient.pesel.pattern}")
    private String pesel;

    @NotNull(message = "{validation.patient.gender.notNull}")
    private Gender gender;
}
