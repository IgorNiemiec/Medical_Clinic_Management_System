package org.example.medical_clinic_management_system.dto.specialization;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StaffSpecializationDetailsDTO
{

    private Long staffId;

    private Long specializationId;

    private String specializationTitle;

    private LocalDate dateCertified;

}
