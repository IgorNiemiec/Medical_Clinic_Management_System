package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;

@Data
public class PatientListItemDto
{

    private Long id;

    private String firstName;
    private String surname;

    private String pesel;
    private String phoneNumber;

}
