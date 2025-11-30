package org.example.medical_clinic_management_system.controller.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;

@Data
@AllArgsConstructor
public class DoctorRegisterRequest
{

    @NotBlank(message = "Email jest wymagany.")
    @Email(message = "Email musi być poprawnym formatem email.")
    private String email;

    @NotBlank(message = "Hasło jest wymagane.")
    @Size(min = 8, message = "Hasło musi mieć co najmniej 8 znaków.")
    private String password;

    @NotBlank(message = "Imię jest wymagane.")
    private String firstName;

    @NotBlank(message = "Nazwisko jest wymagane.")
    private String lastName;

    @Size(max = 12)
    private String officePhoneNumber;

    @NotNull(message = "Specjalizacja jest wymagana.")
    private MedicalStaff.Profession specialization;


}
