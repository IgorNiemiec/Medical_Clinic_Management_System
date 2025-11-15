package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.example.medical_clinic_management_system.model.person.Patient.Gender;

import java.time.LocalDate;

@Data
public class PatientDto
{
    private Long id;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Address cannot be blank")
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
    private String address;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[0-9\\-+]{9,15}$", message = "Phone number must be valid (9–15 digits)")
    private String phone;

    @NotBlank(message = "PESEL cannot be blank")
    @Pattern(regexp = "^[0-9]{11}$", message = "PESEL must be exactly 11 digits")
    private String pesel;

    @NotNull(message = "Gender is required")
    private Gender gender;
}
