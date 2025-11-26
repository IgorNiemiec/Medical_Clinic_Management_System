package org.example.medical_clinic_management_system.controller.pharmacy;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.pharmacy.PrescriptionDetailsDto;
import org.example.medical_clinic_management_system.dto.pharmacy.PrescriptionRequestDto;
import org.example.medical_clinic_management_system.service.pharmacy.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;


    @PostMapping
    public ResponseEntity<PrescriptionDetailsDto> createPrescription(@Valid @RequestBody PrescriptionRequestDto requestDto) {
        PrescriptionDetailsDto newPrescription = prescriptionService.createPrescription(requestDto);
        return new ResponseEntity<>(newPrescription, HttpStatus.CREATED);
    }

    @GetMapping("/{prescriptionId}")
    public ResponseEntity<PrescriptionDetailsDto> getPrescriptionById(@PathVariable Long prescriptionId) {
        PrescriptionDetailsDto prescription = prescriptionService.getPrescriptionById(prescriptionId);
        return ResponseEntity.ok(prescription);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<PrescriptionDetailsDto>> getPrescriptionsByPatient(@PathVariable Long patientId) {
        List<PrescriptionDetailsDto> prescriptions = prescriptionService.getPrescriptionsByPatient(patientId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<PrescriptionDetailsDto>> getPrescriptionsByDoctor(@PathVariable Long doctorId) {
        List<PrescriptionDetailsDto> prescriptions = prescriptionService.getPrescriptionsByDoctor(doctorId);
        return ResponseEntity.ok(prescriptions);
    }

    @DeleteMapping("/{prescriptionId}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long prescriptionId) {
        prescriptionService.deletePrescription(prescriptionId);
        return ResponseEntity.noContent().build();
    }

}