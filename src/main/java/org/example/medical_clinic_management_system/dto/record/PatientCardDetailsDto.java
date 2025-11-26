package org.example.medical_clinic_management_system.dto.record;

import lombok.Data;
import org.example.medical_clinic_management_system.dto.person.PatientDetailsDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PatientCardDetailsDto
{

    private Long id;

    private PatientDetailsDto patient;

    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

    private List<DiseaseCourseDetailsDto> diseaseCourses;

    private int totalDiseaseCourseCount;

}
