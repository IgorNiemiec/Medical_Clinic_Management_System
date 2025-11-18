package org.example.medical_clinic_management_system.dto.record;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class DiseaseProgressionDto {
    private Long id;

    @NotNull(message = "{validation.diseaseProgression.patientCardId.notNull}")
    private Long patientCardId;

    @NotNull(message = "{validation.diseaseProgression.diseaseId.notNull}")
    private Long diseaseId;

    @NotNull(message = "{validation.diseaseProgression.diagnosisDate.notNull}")
    @PastOrPresent(message = "{validation.diseaseProgression.diagnosisDate.pastOrPresent}")
    private LocalDate diagnosisDate;

    @NotBlank(message = "{validation.diseaseProgression.symptoms.notBlank}")
    @Size(min = 3, max = 1000, message = "{validation.diseaseProgression.symptoms.size}")
    private String symptoms;

    @NotBlank(message = "{validation.diseaseProgression.treatment.notBlank}")
    @Size(min = 3, max = 1000, message = "{validation.diseaseProgression.treatment.size}")
    private String treatment;
}
