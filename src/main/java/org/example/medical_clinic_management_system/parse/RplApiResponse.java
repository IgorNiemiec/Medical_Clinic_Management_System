package org.example.medical_clinic_management_system.parse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RplApiResponse {

    @JsonProperty("content")
    private List<RplDrugProductDto> content;

    @JsonProperty("totalPages")
    private int totalPages;

    @JsonProperty("totalElements")
    private long totalElements;
}