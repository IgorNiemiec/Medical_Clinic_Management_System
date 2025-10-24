package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import org.example.medical_clinic_management_system.model.person.Patient.Gender;

import java.time.LocalDate;

@Data
public class PatientDto
{
    private Long id;
    private Long userId;
    private LocalDate dateOfBirth;
    private String address;
    private String phone;
    private String pesel;
    private Gender gender;
}
