package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.example.medical_clinic_management_system.model.person.User.Role;

import java.time.LocalDateTime;

@Data
public class UserDto
{
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    private String surname;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;

    @NotNull(message = "Register date is required")
    private LocalDateTime registerDate;

    private LocalDateTime lastLogin;
}
