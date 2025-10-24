package org.example.medical_clinic_management_system.dto.visit;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PrescriptionDto
{
    private Long id;
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;
    private LocalDate issuedDate;
    private String recommendations;
}
