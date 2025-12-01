package org.example.medical_clinic_management_system.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.person.Receptionist;
import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.security.jwt.JwtUtil;
import org.example.medical_clinic_management_system.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController
{

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthService authService;



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        try
        {

            System.out.println(loginRequest.getEmail() + " - " + loginRequest.getPassword());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            User userDetails = (User) authentication.getPrincipal();

            String jwt = jwtUtil.generateToken(userDetails);

            System.out.println("Zalogowano pomyślnie. Rola: " + authentication.getAuthorities());

            return ResponseEntity.ok(AuthResponse.builder()
                    .token(jwt)
                    .email(userDetails.getEmail())
                    .role(userDetails.getRole().name())
                    .userId(userDetails.getId())
                    .build());



        }
        catch (Exception e)
        {

            System.err.println(e.getMessage());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }









    }



    @PostMapping("/register/patient")
    @PreAuthorize("hasRole('RECEPTIONIST') or hasRole('ADMIN')")
    public ResponseEntity<Patient> registerPatient(
            @Valid @RequestBody PatientRegisterRequest request,
            @AuthenticationPrincipal User userDetails) {

        if (userDetails == null)
        {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }


        try
        {

            Patient newPatient = authService.registerPatient(request, userDetails.getId());
            return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
        } catch (IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/register/medicalstaff")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicalStaff> registerDoctor(@Valid @RequestBody DoctorRegisterRequest request) {
        try
        {
            MedicalStaff newMedicalStaff = authService.registerMedicalStaff(request);
            return new ResponseEntity<>(newMedicalStaff, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/register/receptionist")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Receptionist> registerReceptionist(@Valid @RequestBody ReceptionistRegisterRequest request) {
        try {
            Receptionist newReceptionist = authService.registerReceptionist(request);
            return new ResponseEntity<>(newReceptionist, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }



}
