package org.example.medical_clinic_management_system.dto.record;

import lombok.Data;
import org.example.medical_clinic_management_system.model.record.DiseaseCourse;

import java.time.LocalDateTime;

@Data
public class DiseaseCourseRequestDto
{

    private Long patientCardId;

    private Long icdCodeId;

    private Long medicalStaffId;

    private LocalDateTime diagnosisDate;

    private String description;

    private DiseaseCourse.DiseaseStatus status;


}
