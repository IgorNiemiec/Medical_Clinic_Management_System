package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import org.example.medical_clinic_management_system.model.person.User.Role;

import java.time.LocalDateTime;

@Data
public class UserDto
{
    private Long id;
    private String firstName;
    private String surname;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime registerDate;
    private LocalDateTime lastLogin;
}
