package org.example.medical_clinic_management_system.tests;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.medical_clinic_management_system.controller.auth.*;
import org.example.medical_clinic_management_system.model.person.*;
import org.example.medical_clinic_management_system.security.jwt.JwtUtil;
import org.example.medical_clinic_management_system.service.auth.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest
{

    @Configuration
    public static class MockingConfig {
        @Bean
        public AuthService authService() {
            return mock(AuthService.class);
        }

        @Bean
        public AuthenticationManager authenticationManager() {
            return mock(AuthenticationManager.class);
        }

        @Bean
        public JwtUtil jwtUtil() {
            return mock(JwtUtil.class);
        }
    }


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

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
        LoginRequest loginRequest = new LoginRequest(TEST_EMAIL, "politechnikaKlinika123");

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
                .andExpect((ResultMatcher) jsonPath("$.token").value(expectedJwt))
                .andExpect((ResultMatcher) jsonPath("$.email").value(TEST_EMAIL))
                .andExpect((ResultMatcher) jsonPath("$.role").value(Role.ROLE_ADMIN.name()))
                .andExpect((ResultMatcher) jsonPath("$.userId").value(1L));
    }


    @Test
    @WithMockUser(roles = "RECEPTIONIST", username = "recepcjonista@klinika.pl", authorities = "ROLE_RECEPTIONIST")
    void registerPatient_AsReceptionist_Success() throws Exception {

        PatientRegisterRequest request = new PatientRegisterRequest("Jan", "Kowalski", "jan@test.pl", "haslo123", "12345678901", LocalDate.of(1990, 1, 1), "Adres", "500100200", Patient.Gender.MALE);
        Patient mockPatient = Patient.builder().id(5L).user(User.builder().email("jan@test.pl").build()).build();


        when(authService.registerPatient(any(PatientRegisterRequest.class), any(Long.class)))
                .thenReturn(mockPatient);


        mockMvc.perform(post("/api/auth/register/patient")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }



    @Test
    void registerPatient_Unauthenticated_ReturnsUnauthorized() throws Exception {

        PatientRegisterRequest request = new PatientRegisterRequest("Jan", "Kowalski", "jan@test.pl", "haslo123", "12345678901", LocalDate.of(1990, 1, 1), "Adres", "500100200", Patient.Gender.MALE);


        mockMvc.perform(post("/api/auth/register/patient")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }


    @Test
    @WithMockUser(roles = "RECEPTIONIST")
    void registerPatient_ServiceThrowsConflict_ReturnsBadRequest() throws Exception {

        PatientRegisterRequest request = new PatientRegisterRequest("Jan", "Kowalski", "jan@test.pl", "haslo123", "12345678901", LocalDate.of(1990, 1, 1), "Adres", "500100200", Patient.Gender.MALE);

        when(authService.registerPatient(any(PatientRegisterRequest.class), any(Long.class)))
                .thenThrow(new IllegalArgumentException("Użytkownik już istnieje."));


        mockMvc.perform(post("/api/auth/register/patient")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }


    @Test
    @WithMockUser(roles = "ADMIN")
    void registerMedicalStaff_AsAdmin_Success() throws Exception {

        DoctorRegisterRequest request = new DoctorRegisterRequest("Anna", "Nowak", "anna@lekarz.pl", "pass2024", "111222333", MedicalStaff.Profession.NURSE);
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
    @WithMockUser(roles = "USER")
    void registerMedicalStaff_AsUnauthorizedUser_ReturnsForbidden() throws Exception {

        DoctorRegisterRequest request = new DoctorRegisterRequest("Anna", "Nowak", "anna@lekarz.pl", "pass2024", "111222333", MedicalStaff.Profession.DOCTOR);


        // ACT & ASSERT
        mockMvc.perform(post("/api/auth/register/medicalstaff")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }



    @Test
    @WithMockUser(roles = "ADMIN")
    void registerReceptionist_AsAdmin_Success() throws Exception {

        ReceptionistRegisterRequest request = new ReceptionistRegisterRequest("Piotr", "Zając", "piotr@rejestracja.pl", "pass123", "444555666");
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

        ReceptionistRegisterRequest request = new ReceptionistRegisterRequest("Piotr", "Zając", "piotr@rejestracja.pl", "pass123", "444555666");

        mockMvc.perform(post("/api/auth/register/receptionist")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isForbidden());
    }




}
