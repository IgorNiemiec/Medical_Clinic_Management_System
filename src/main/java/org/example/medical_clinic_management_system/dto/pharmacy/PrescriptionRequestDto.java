package org.example.medical_clinic_management_system.dto.pharmacy;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionRequestDto
{

    private Long patientId;

    private Long doctorId;

    private Long drugId;

    private LocalDate expirationDate;

    private String dosageInstructions;

    private Integer quantity;



}
