package org.example.medical_clinic_management_system.parse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RplDrugProductDto
{

    @JsonProperty("medicinalProductName")
    private String medicinalProductName;


    @JsonProperty("commonName")
    private String commonName;


    @JsonProperty("pharmaceuticalFormName")
    private String formName;

    @JsonProperty("medicinalProductPower")
    private String medicinalProductPower;


    @JsonProperty("atcCode")
    private String atcCode;

    @JsonProperty("id")
    private Long id;
}