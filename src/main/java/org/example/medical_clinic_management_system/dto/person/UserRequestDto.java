package org.example.medical_clinic_management_system.dto.person;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.medical_clinic_management_system.model.person.User;


@Data
public class UserRequestDto
{

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
    @Size(min = 8, max = 255, message = "{validation.user.password.size}")
    private String password;


    @NotNull(message = "{validation.user.role.notNull}")
    private User.Role role;



}
