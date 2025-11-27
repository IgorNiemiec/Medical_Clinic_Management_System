package org.example.medical_clinic_management_system.dto.person;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.medical_clinic_management_system.model.person.Patient;

import java.time.LocalDate;

@Data
public class PatientRequestDto
{

    private Long userId;

    private Long registeredByEmployeeId;

    @NotNull(message = "{validation.patient.dateOfBirth.notNull}")
    @PastOrPresent(message = "{validation.patient.dateOfBirth.pastOrPresent}")
    private LocalDate dateOfBirth;

    @NotBlank(message = "{validation.patient.address.notBlank}")
    @Size(min = 5, max = 255, message = "{validation.patient.address.size}")
    private String address;

    @NotBlank(message = "{validation.patient.phoneNumber.notBlank}")
    @Pattern(regexp = "^(\\+\\d{1,3})?[ -]?\\(?\\d{2,3}\\)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}$",
            message = "{validation.patient.phoneNumber.pattern}")
    private String phoneNumber;

    @NotBlank(message = "{validation.patient.pesel.notBlank}")
    @Pattern(regexp = "^\\d{11}$", message = "{validation.patient.pesel.pattern}")
    private String pesel;

    @NotNull(message = "{validation.patient.gender.notNull}")
    private Patient.Gender gender;


}
