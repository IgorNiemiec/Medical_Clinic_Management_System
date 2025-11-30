package org.example.medical_clinic_management_system.controller.pharmacy;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.pharmacy.PrescriptionDetailsDto;
import org.example.medical_clinic_management_system.dto.pharmacy.PrescriptionRequestDto;
import org.example.medical_clinic_management_system.service.pharmacy.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;


    @PostMapping
    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    public ResponseEntity<PrescriptionDetailsDto> createPrescription(@Valid @RequestBody PrescriptionRequestDto requestDto) {
        PrescriptionDetailsDto newPrescription = prescriptionService.create(requestDto);
        return new ResponseEntity<>(newPrescription, HttpStatus.CREATED);
    }

    @GetMapping("/{prescriptionId}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<PrescriptionDetailsDto> getPrescriptionById(@PathVariable Long prescriptionId) {
        PrescriptionDetailsDto prescription = prescriptionService.findById(prescriptionId);
        return ResponseEntity.ok(prescription);
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<List<PrescriptionDetailsDto>> getPrescriptionsByPatient(@PathVariable Long patientId) {
        List<PrescriptionDetailsDto> prescriptions = prescriptionService.findByPatient(patientId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/doctor/{doctorId}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<List<PrescriptionDetailsDto>> getPrescriptionsByDoctor(@PathVariable Long doctorId) {
        List<PrescriptionDetailsDto> prescriptions = prescriptionService.findByDoctor(doctorId);
        return ResponseEntity.ok(prescriptions);
    }

    @DeleteMapping("/{prescriptionId}")
    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long prescriptionId) {
        prescriptionService.deleteById(prescriptionId);
        return ResponseEntity.noContent().build();
    }

}