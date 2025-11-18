package org.example.medical_clinic_management_system.dto.visit;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PrescriptionDto {
    private Long id;

    @NotNull(message = "{validation.prescription.appointmentId.notNull}")
    private Long appointmentId;

    @NotNull(message = "{validation.prescription.patientId.notNull}")
    private Long patientId;

    @NotNull(message = "{validation.prescription.doctorId.notNull}")
    private Long doctorId;

    @NotNull(message = "{validation.prescription.issuedDate.notNull}")
    @PastOrPresent(message = "{validation.prescription.issuedDate.pastOrPresent}")
    private LocalDate issuedDate;

    @NotBlank(message = "{validation.prescription.recommendations.notBlank}")
    @Size(min = 5, max = 2000, message = "{validation.prescription.recommendations.size}")
    private String recommendations;
}
