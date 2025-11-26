package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EmployeeDetailsDto
{

    private Long id;

    private Long userId;
    private String firstName;
    private String surname;
    private String email;
    private String role;
    private LocalDateTime registerDate;
    private LocalDate hireDate;
    private String servicePhone;


}
