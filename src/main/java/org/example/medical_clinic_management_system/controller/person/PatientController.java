package org.example.medical_clinic_management_system.controller.person;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.PatientDetailsDto;
import org.example.medical_clinic_management_system.dto.person.PatientDto;
import org.example.medical_clinic_management_system.dto.person.PatientListItemDto;
import org.example.medical_clinic_management_system.dto.person.PatientRequestDto;
import org.example.medical_clinic_management_system.service.person.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController
{

    private final PatientService patientService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<PatientDetailsDto> registerPatient(@Valid @RequestBody PatientRequestDto requestDto) {
        PatientDetailsDto newPatient = patientService.registerPatient(requestDto);
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }


    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<List<PatientListItemDto>> getAllPatients() {
        List<PatientListItemDto> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{patientId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<PatientDetailsDto> getPatientById(@PathVariable Long patientId) {
        PatientDetailsDto patient = patientService.getPatientById(patientId);
        return ResponseEntity.ok(patient);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<PatientDetailsDto> getPatientByPesel(@RequestParam String pesel) {

        PatientDetailsDto patient = patientService.getPatientByPesel(pesel);
        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{patientId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<PatientDetailsDto> updatePatient(@PathVariable Long patientId,
                                                           @Valid @RequestBody PatientRequestDto requestDto) {
        PatientDetailsDto updatedPatient = patientService.updatePatient(patientId, requestDto);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{patientId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<Void> deletePatient(@PathVariable Long patientId) {
        patientService.deletePatient(patientId);
        return ResponseEntity.noContent().build();
    }




}
