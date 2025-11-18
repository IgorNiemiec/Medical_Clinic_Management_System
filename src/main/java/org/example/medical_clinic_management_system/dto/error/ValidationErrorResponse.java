package org.example.medical_clinic_management_system.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ValidationErrorResponse {
    private String title;
    private List<FieldErrorDto> errors;
}
