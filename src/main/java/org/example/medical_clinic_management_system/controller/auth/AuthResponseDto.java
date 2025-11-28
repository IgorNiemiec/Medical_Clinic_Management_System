package org.example.medical_clinic_management_system.controller.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto
{
    private String token;
    private String firstName;
    private String surname;
    private String role;
    private Long userId;
}
