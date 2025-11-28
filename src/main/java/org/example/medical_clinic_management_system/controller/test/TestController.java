package org.example.medical_clinic_management_system.controller.test;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController
{

    @GetMapping("/user")
    public String userAccess() {
        return "Zawartość dla każdego zalogowanego użytkownika (Pacjent/Lekarz/Recepcjonista/Admin).";
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasRole('DOCTOR')")
    public String doctorAccess() {
        return "Zawartość tylko dla Lekarzy. Dostęp autoryzowany rolą.";
    }

    @GetMapping("/receptionist")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    public String receptionistAccess() {
        return "Zawartość tylko dla Recepcjonistów. Tutaj będzie np. lista wizyt.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Zawartość tylko dla Administratorów. Zazwyczaj zarządzanie użytkownikami/konfiguracją.";
    }



}
