package org.example.medical_clinic_management_system.controller.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReceptionistRegisterRequest
{

    @NotBlank(message = "Email (login) jest wymagany.")
    @Email(message = "Email musi być poprawnym formatem email.")
    private String email;

    @NotBlank(message = "Hasło jest wymagane.")
    @Size(min = 8, message = "Hasło musi mieć co najmniej 8 znaków.")
    private String password;

    @NotBlank(message = "Imię jest wymagane.")
    private String firstName;

    @NotBlank(message = "Nazwisko jest wymagane.")
    private String surname;

    @NotBlank(message = "Numer telefonu służbowego jest wymagany.")
    @Size(max = 12)
    private String servicePhone;

}
