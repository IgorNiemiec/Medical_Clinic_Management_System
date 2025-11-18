package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import jakarta.validation.constraints.*;
import org.example.medical_clinic_management_system.model.person.User.Role;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;

    @NotBlank(message = "{validation.user.firstName.notBlank}")
    @Size(min = 2, max = 50, message = "{validation.user.firstName.size}")
    private String firstName;

    @NotBlank(message = "{validation.user.surname.notBlank}")
    @Size(min = 2, max = 50, message = "{validation.user.surname.size}")
    private String surname;

    @NotBlank(message = "{validation.user.email.notBlank}")
    @Email(message = "{validation.user.email.email}")
    @Size(max = 100, message = "{validation.user.email.size}")
    private String email;

    @NotBlank(message = "{validation.user.password.notBlank}")
    @Size(min = 6, max = 20, message = "{validation.user.password.size}")
    private String password;

    @NotNull(message = "{validation.user.role.notNull}")
    private Role role;

    @NotNull(message = "{validation.user.registerDate.notNull}")
    private LocalDateTime registerDate;

    private LocalDateTime lastLogin;
}
