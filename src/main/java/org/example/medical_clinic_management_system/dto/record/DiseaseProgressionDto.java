package org.example.medical_clinic_management_system.dto.record;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DiseaseProgressionDto
{
    private Long id;
    private Long patientCardId;
    private Long diseaseId;
    private LocalDate diagnosisDate;
    private String symptoms;
    private String treatment;
}
