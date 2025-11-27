package org.example.medical_clinic_management_system.dto.record;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.example.medical_clinic_management_system.model.record.DiseaseCourse;

import java.time.LocalDateTime;

@Data
public class DiseaseCourseRequestDto
{

    @NotNull(message = "{validation.diseaseCourse.patientCardId.notNull}")
    private Long patientCardId;

    @NotNull(message = "{validation.diseaseCourse.icdCodeId.notNull}")
    @Min(value = 1, message = "{validation.diseaseCourse.icdCodeId.min}")
    private Long icdCodeId;

    @NotNull(message = "{validation.diseaseCourse.medicalStaffId.notNull}")
    @Min(value = 1, message = "{validation.diseaseCourse.medicalStaffId.min}")
    private Long medicalStaffId;

    @NotNull(message = "{validation.diseaseCourse.diagnosisDate.notNull}")
    @FutureOrPresent(message = "{validation.diseaseCourse.diagnosisDate.futureOrPresent}")
    private LocalDateTime diagnosisDate;

    @NotBlank(message = "{validation.diseaseCourse.description.notBlank}")
    @Size(min = 10, max = 5000, message = "{validation.diseaseCourse.description.size}")
    private String description;

    @NotNull(message = "{validation.diseaseCourse.status.notNull}")
    private DiseaseCourse.DiseaseStatus status;


}
