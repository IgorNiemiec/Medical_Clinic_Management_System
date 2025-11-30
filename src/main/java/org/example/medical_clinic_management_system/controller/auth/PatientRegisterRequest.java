package org.example.medical_clinic_management_system.controller.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.medical_clinic_management_system.model.person.Patient;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PatientRegisterRequest
{

    @NotBlank(message = "Imie jest wymagana.")
    @Email(message = "Imie musi być poprawnym formatem")
    private String firstName;

    @NotBlank(message = "Nazwisko jest wymagane")
    private String surname;

    @NotBlank(message = "Imie jest wymagana.")
    @Email(message = "email musi być poprawnym formatem")
    private String email;

    @NotBlank(message = "Hasło jest wymagane.")
    @Size(min = 8, message = "Hasło musi mieć co najmniej 8 znaków.")
    private String password;

    @NotBlank(message = "PESEL jest wymagany.")
    @Size(min = 11, max = 11, message = "PESEL musi mieć 11 cyfr.")
    private String pesel;

    private LocalDate dateOfBirth;

    @Size(max = 255)
    private String address;

    @Size(max = 12)
    private String phoneNumber;

    private Patient.Gender gender;


}
