package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import org.example.medical_clinic_management_system.model.person.Patient;

import java.time.LocalDate;

@Data
public class PatientDetailsDto
{

    private Long id;

    private Long userId;
    private String firstName;
    private String surname;
    private String email;

    private Long registeredByEmployeeId;
    private String registeredByEmployeeFullName;

    private LocalDate dateOfBirth;
    private String address;
    private String phoneNumber;
    private String pesel;
    private Patient.Gender gender;


}
