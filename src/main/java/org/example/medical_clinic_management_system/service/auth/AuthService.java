package org.example.medical_clinic_management_system.service.auth;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.controller.auth.DoctorRegisterRequest;
import org.example.medical_clinic_management_system.controller.auth.PatientRegisterRequest;
import org.example.medical_clinic_management_system.controller.auth.ReceptionistRegisterRequest;
import org.example.medical_clinic_management_system.model.person.*;
import org.example.medical_clinic_management_system.repository.person.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService
{

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final MedicalStaffRepository medicalStaffRepository;
    private final ReceptionistRepository receptionistRepository;

    private Employee getEmployeeByUserId(Long userId)
    {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Obecny użytkownik (" + userId + ") nie istnieje."));

        return employeeRepository.findByUser(user.getId())
                .orElseThrow(() -> new IllegalStateException("Obecny użytkownik (" + user.getEmail() + ") nie jest pracownikiem i nie może rejestrować pacjentów."));
    }



    @Transactional
    public Patient registerPatient(PatientRegisterRequest request, Long registeredByUserId) {


        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Użytkownik o podanym loginie (email) już istnieje.");
        }
        if (patientRepository.findByPesel(request.getPesel()).isPresent()) {
            throw new IllegalArgumentException("Pacjent o podanym numerze PESEL już istnieje.");
        }


        Employee registeredBy = getEmployeeByUserId(registeredByUserId);


        User newUser = User.builder()
                .email(request.getEmail()) // POPRAWKA: Dodano email
                .firstName(request.getFirstName())
                .surname(request.getSurname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_PATIENT)
                .registerDate(LocalDateTime.now()) // POPRAWKA: Dodano datę rejestracji
                .build();
        userRepository.save(newUser);


        Patient newPatient = Patient.builder()
                .user(newUser)
                .pesel(request.getPesel())
                .dateOfBirth(request.getDateOfBirth())
                .address(request.getAddress())
                .phoneNumber(request.getPhoneNumber())
                .gender(request.getGender())
                .registeredBy(registeredBy)
                .build();

        return patientRepository.save(newPatient);
    }

    @Transactional
    public MedicalStaff registerMedicalStaff(DoctorRegisterRequest request) { // Zmieniam nazwę metody, by była zgodna z encją

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Użytkownik o podanym loginie (email) już istnieje.");
        }


        User newUser = User.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .surname(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_DOCTOR)
                .registerDate(LocalDateTime.now())
                .build();
        userRepository.save(newUser);


        Employee newEmployee = Employee.builder()
                .user(newUser)
                .servicePhone(request.getOfficePhoneNumber())
                .hireDate(LocalDate.now()) // Dodajemy datę zatrudnienia
                .build();
        employeeRepository.save(newEmployee);


        MedicalStaff newMedicalStaff = MedicalStaff.builder()
                .employee(newEmployee)
                .profession(request.getSpecialization())
                .build();


        return medicalStaffRepository.save(newMedicalStaff);
    }



    @Transactional
    public Receptionist registerReceptionist(ReceptionistRegisterRequest request) {


        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Użytkownik o podanym emailu (login) już istnieje.");
        }


        User newUser = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_RECEPTIONIST)
                .firstName(request.getFirstName())
                .surname(request.getSurname())
                .registerDate(LocalDateTime.now())
                .build();
        userRepository.save(newUser);


        Employee newEmployee = Employee.builder()
                .user(newUser)
                .servicePhone(request.getServicePhone())
                .hireDate(LocalDate.now())
                .build();
        employeeRepository.save(newEmployee);


        Receptionist newReceptionist = Receptionist.builder()
                .employee(newEmployee)
                .build();

        return receptionistRepository.save(newReceptionist);
    }







}
