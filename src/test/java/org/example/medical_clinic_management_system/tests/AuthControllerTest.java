package org.example.medical_clinic_management_system.tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.medical_clinic_management_system.controller.auth.*;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.person.Receptionist;
import org.example.medical_clinic_management_system.model.person.Role;
import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.security.config.SecurityConfig;
import org.example.medical_clinic_management_system.security.details.UserDetailsServiceImplementation;
import org.example.medical_clinic_management_system.security.jwt.JwtAuthenticationFilter;
import org.example.medical_clinic_management_system.security.jwt.JwtUtil;
import org.example.medical_clinic_management_system.service.auth.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity; // Dodano import springSecurity()
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import({SecurityConfig.class})
public class AuthControllerTest
{


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserDetailsServiceImplementation userDetailsServiceImplementation;



    @Autowired
    private WebApplicationContext context;


    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    void authenticateUser_Success() throws Exception {

        final String TEST_EMAIL = "politechnika@klinika.pl";
        LoginRequest loginRequest = new LoginRequest(TEST_EMAIL, "StrongPass123!");


        User userDetails = User.builder()
                .id(1L)
                .email(TEST_EMAIL)
                .role(Role.ROLE_ADMIN)
                .build();
        String expectedJwt = "mocked.jwt.token";



        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtUtil.generateToken(userDetails)).thenReturn(expectedJwt);


        mockMvc.perform(post("/api/auth/login")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(expectedJwt))
                .andExpect(jsonPath("$.email").value(TEST_EMAIL))
                .andExpect(jsonPath("$.role").value(Role.ROLE_ADMIN.name()))
                .andExpect(jsonPath("$.userId").value(1L));
    }


    @Test
    void registerPatient_AsReceptionist_Success() throws Exception {

        User mockReceptionistUser = User.builder()
                .id(99L)
                .email("recepcjonista@klinika.pl")
                .role(Role.ROLE_RECEPTIONIST)
                .build();

        PatientRegisterRequest request = new PatientRegisterRequest("jan@test.pl", "StrongPass123!", "Jan", "Kowalski", "12345678901", LocalDate.of(1990, 1, 1), Patient.Gender.MALE, "500100200","Adres");

        User registeredUser = User.builder().email("jan@test.pl").role(Role.ROLE_PATIENT).build();
        Patient mockPatient = Patient.builder().id(5L).user(registeredUser).build();

        when(authService.registerPatient(any(PatientRegisterRequest.class), any(Long.class)))
                .thenReturn(mockPatient);

        mockMvc.perform(post("/api/auth/register/patient")
                        .with(csrf())
                        .with(user(mockReceptionistUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }



    @Test
    void registerPatient_Unauthenticated_ReturnsForbidden() throws Exception {

        PatientRegisterRequest request = new PatientRegisterRequest("jan@test.pl", "StrongPass123!", "Jan", "Kowalski", "12345678901", LocalDate.of(1990, 1, 1), Patient.Gender.MALE, "500100200", "wodna4");


        mockMvc.perform(post("/api/auth/register/patient")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }


    @Test
    void registerPatient_ServiceThrowsConflict_ReturnsBadRequest() throws Exception {

        User mockReceptionistUser = User.builder()
                .id(99L)
                .email("recepcjonista@klinika.pl")
                .role(Role.ROLE_RECEPTIONIST)
                .build();

        PatientRegisterRequest request = new PatientRegisterRequest("jan@test.pl", "StrongPass123!", "Jan", "Kowalski", "12345678901", LocalDate.of(1990, 1, 1), Patient.Gender.MALE, "500100200", "wodna4");


        when(authService.registerPatient(any(PatientRegisterRequest.class), any(Long.class)))
                .thenThrow(new IllegalArgumentException("Użytkownik już istnieje."));



        mockMvc.perform(post("/api/auth/register/patient")
                        .with(csrf())
                        .with(user(mockReceptionistUser))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void registerMedicalStaff_AsAdmin_Success() throws Exception {

        DoctorRegisterRequest request = new DoctorRegisterRequest("anna@lekarz.pl", "StrongPass123!", "Anna", "Kozub",  MedicalStaff.Profession.DOCTOR,"111222333");
        MedicalStaff mockStaff = MedicalStaff.builder().id(10L).profession(MedicalStaff.Profession.NURSE).build();


        when(authService.registerMedicalStaff(any(DoctorRegisterRequest.class)))
                .thenReturn(mockStaff);


        mockMvc.perform(post("/api/auth/register/medicalstaff")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }


    @Test
    @WithMockUser(roles = "RECEPTIONIST")
    void registerMedicalStaff_AsUnauthorizedUser_ReturnsForbidden() throws Exception {

        DoctorRegisterRequest request = new DoctorRegisterRequest("anna@lekarz.pl", "StrongPass123!", "Anna", "Kozub",  MedicalStaff.Profession.DOCTOR,"111222333");


        mockMvc.perform(post("/api/auth/register/medicalstaff")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }



    @Test
    @WithMockUser(roles = "ADMIN")
    void registerReceptionist_AsAdmin_Success() throws Exception {

        ReceptionistRegisterRequest request = new ReceptionistRegisterRequest("maciek@rejestracja.pl", "StrongPass123!", "Maciej", "Pompa", "444555666");
        Receptionist mockReceptionist = Receptionist.builder().id(12L).build();


        when(authService.registerReceptionist(any(ReceptionistRegisterRequest.class)))
                .thenReturn(mockReceptionist);


        mockMvc.perform(post("/api/auth/register/receptionist")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = "RECEPTIONIST")
    void registerReceptionist_AsReceptionist_ReturnsForbidden() throws Exception {

        ReceptionistRegisterRequest request = new ReceptionistRegisterRequest("piotr@rejestracja.pl", "StrongPass123!", "Piotr", "Nowak", "636030426");

        mockMvc.perform(post("/api/auth/register/receptionist")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }
}