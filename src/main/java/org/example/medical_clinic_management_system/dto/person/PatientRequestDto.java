package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import org.example.medical_clinic_management_system.model.person.Patient;

import java.time.LocalDate;

@Data
public class PatientRequestDto
{

    private Long userId;

    private Long registeredByEmployeeId;

    private LocalDate dateOfBirth;

    private String address;

    private String phoneNumber;

    private String pesel;

    private Patient.Gender gender;


}
