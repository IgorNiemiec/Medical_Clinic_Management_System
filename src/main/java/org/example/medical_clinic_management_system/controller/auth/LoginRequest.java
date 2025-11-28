package org.example.medical_clinic_management_system.controller.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest
{
        @NotBlank
        private String email;
        @NotBlank
        private String password;
}
