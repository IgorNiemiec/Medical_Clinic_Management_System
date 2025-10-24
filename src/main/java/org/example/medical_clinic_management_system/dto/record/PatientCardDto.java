package org.example.medical_clinic_management_system.dto.record;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientCardDto
{
    private Long id;
    private Long patientId;
    private LocalDate createdAt;
    private String medicalHistory;
    private String allergies;
}
