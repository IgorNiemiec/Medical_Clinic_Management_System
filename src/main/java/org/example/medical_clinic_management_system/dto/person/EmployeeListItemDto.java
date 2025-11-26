package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;

@Data
public class EmployeeListItemDto
{
    private Long id;
    private Long userId;
    private String firstName;
    private String surname;
    private String servicePhone;
}
