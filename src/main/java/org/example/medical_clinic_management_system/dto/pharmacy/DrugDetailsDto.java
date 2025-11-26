package org.example.medical_clinic_management_system.dto.pharmacy;

import lombok.Data;

@Data
public class DrugDetailsDto
{

    private Long id;

    private String productName;

    private String commonName;

    private String form;

    private String atcCode;

    private String gtinNumber;

    private boolean isActive;

}
