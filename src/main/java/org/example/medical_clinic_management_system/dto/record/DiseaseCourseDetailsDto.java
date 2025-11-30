package org.example.medical_clinic_management_system.dto.record;

import lombok.Data;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffDetailsDto;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffDto;
import org.example.medical_clinic_management_system.model.record.DiseaseCourse;

import java.time.LocalDateTime;

@Data
public class DiseaseCourseDetailsDto
{

    private Long id;
    private Long patientCardId;
    private ICDCodeDto icdCode;
    private MedicalStaffDetailsDto medicalStaff;
    private LocalDateTime diagnosisDate;
    private String description;
    private DiseaseCourse.DiseaseStatus status;

}
