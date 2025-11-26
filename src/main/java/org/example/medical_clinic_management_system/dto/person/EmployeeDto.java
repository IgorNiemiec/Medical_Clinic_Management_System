package org.example.medical_clinic_management_system.dto.person;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeDto
{
    private Long id;

    private LocalDate hireDate;

    private String servicePhone;

}
