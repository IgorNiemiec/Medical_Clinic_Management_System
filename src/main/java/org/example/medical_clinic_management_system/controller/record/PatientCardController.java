package org.example.medical_clinic_management_system.controller.record;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.PatientCardDetailsDto;
import org.example.medical_clinic_management_system.dto.record.PatientCardDto;
import org.example.medical_clinic_management_system.dto.record.PatientCardRequestDto;
import org.example.medical_clinic_management_system.service.record.PatientCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient-cards")
@RequiredArgsConstructor
public class PatientCardController
{

    private final PatientCardService patientCardService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
    public ResponseEntity<PatientCardDetailsDto> createPatientCard(@Valid @RequestBody PatientCardRequestDto requestDto) {
        PatientCardDetailsDto newCard = patientCardService.createPatientCard(requestDto);
        return new ResponseEntity<>(newCard, HttpStatus.CREATED);
    }

    @GetMapping("/{cardId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
    public ResponseEntity<PatientCardDetailsDto> getPatientCardById(@PathVariable Long cardId) {
        PatientCardDetailsDto card = patientCardService.getPatientCardById(cardId);
        return ResponseEntity.ok(card);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
    public ResponseEntity<PatientCardDetailsDto> getPatientCardByPatientId(@RequestParam Long patientId) {
        PatientCardDetailsDto card = patientCardService.getPatientCardByPatientId(patientId);
        return ResponseEntity.ok(card);
    }

    @DeleteMapping("/{cardId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
    public ResponseEntity<Void> deletePatientCard(@PathVariable Long cardId) {
        patientCardService.deletePatientCard(cardId);
        return ResponseEntity.noContent().build();
    }





}
