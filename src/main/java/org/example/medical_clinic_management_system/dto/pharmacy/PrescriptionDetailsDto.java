package org.example.medical_clinic_management_system.dto.pharmacy;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionDetailsDto
{

    private Long id;

    private Long patientId;
    private String patientFullName;

    private Long doctorId;
    private String doctorFullName;

    private Long drugId;
    private String drugProductName;
    private String drugCommonName;
    private String drugForm;
    private String drugAtcCode;

    private LocalDate expirationDate;
    private String dosageInstructions;
    private Integer quantity;


}
