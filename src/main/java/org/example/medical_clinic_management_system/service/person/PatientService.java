package org.example.medical_clinic_management_system.service.person;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.PatientDetailsDto;
import org.example.medical_clinic_management_system.dto.person.PatientDto;
import org.example.medical_clinic_management_system.dto.person.PatientListItemDto;
import org.example.medical_clinic_management_system.dto.person.PatientRequestDto;
import org.example.medical_clinic_management_system.mapper.person.PatientMapper;
import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.repository.person.EmployeeRepository;
import org.example.medical_clinic_management_system.repository.person.PatientRepository;
import org.example.medical_clinic_management_system.repository.person.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService
{

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PatientMapper patientMapper;

    @Transactional
    public PatientDetailsDto registerPatient(PatientRequestDto requestDto) {

        if (patientRepository.existsByPesel(requestDto.getPesel())) {
            throw new RuntimeException("Pacjent o numerze PESEL: " + requestDto.getPesel() + " jest już zarejestrowany.");
        }


        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Konto użytkownika (User) o ID: " + requestDto.getUserId() + " nie zostało znalezione."));

        Employee registeredBy = employeeRepository.findById(requestDto.getRegisteredByEmployeeId())
                .orElseThrow(() -> new RuntimeException("Pracownik rejestrujący (Employee) o ID: " + requestDto.getRegisteredByEmployeeId() + " nie został znaleziony."));

        Patient patient = patientMapper.toEntity(requestDto);

        patient.setUser(user);
        patient.setRegisteredBy(registeredBy);

        Patient savedPatient = patientRepository.save(patient);

        return patientMapper.toDetailsDto(savedPatient);
    }


    @Transactional
    public PatientDetailsDto getPatientById(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Pacjent nie został znaleziony o ID: " + patientId));

        return patientMapper.toDetailsDto(patient);
    }

    @Transactional
    public PatientDetailsDto getPatientByPesel(String pesel) {
        Patient patient = patientRepository.findByPesel(pesel)
                .orElseThrow(() -> new RuntimeException("Pacjent nie został znaleziony o numerze PESEL: " + pesel));

        return patientMapper.toDetailsDto(patient);
    }

    @Transactional
    public List<PatientListItemDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.toListItemDtoList(patients);
    }

    @Transactional
    public PatientDetailsDto updatePatient(Long patientId, PatientRequestDto requestDto) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Pacjent nie został znaleziony o ID: " + patientId));

        if (!patient.getPesel().equals(requestDto.getPesel()) && patientRepository.existsByPesel(requestDto.getPesel())) {
            throw new RuntimeException("Numer PESEL: " + requestDto.getPesel() + " jest już zajęty przez innego pacjenta.");
        }

        Employee registeredBy = employeeRepository.findById(requestDto.getRegisteredByEmployeeId())
                .orElseThrow(() -> new RuntimeException("Pracownik rejestrujący o ID: " + requestDto.getRegisteredByEmployeeId() + " nie został znaleziony."));

        patient.setDateOfBirth(requestDto.getDateOfBirth());
        patient.setAddress(requestDto.getAddress());
        patient.setPhoneNumber(requestDto.getPhoneNumber());
        patient.setPesel(requestDto.getPesel());
        patient.setGender(requestDto.getGender());
        patient.setRegisteredBy(registeredBy);


        Patient updatedPatient = patientRepository.save(patient);
        return patientMapper.toDetailsDto(updatedPatient);
    }

    @Transactional
    public void deletePatient(Long patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new RuntimeException("Pacjent nie został znaleziony o ID: " + patientId);
        }

        patientRepository.deleteById(patientId);
    }






}
