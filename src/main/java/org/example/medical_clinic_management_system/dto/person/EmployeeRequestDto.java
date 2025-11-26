package org.example.medical_clinic_management_system.dto.person;


import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequestDto
{

    private Long userId;

    private LocalDate hireDate;

    private String servicePhone;

}
