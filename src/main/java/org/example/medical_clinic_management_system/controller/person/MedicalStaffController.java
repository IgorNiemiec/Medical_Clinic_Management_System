package org.example.medical_clinic_management_system.controller.person;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffDetailsDto;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffDto;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffRequestDto;
import org.example.medical_clinic_management_system.service.person.MedicalStaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-staff")
@RequiredArgsConstructor
public class MedicalStaffController
{

    private final MedicalStaffService medicalStaffService;

    @GetMapping
    public ResponseEntity<List<MedicalStaffDetailsDto>> getAllMedicalStaff() {
        List<MedicalStaffDetailsDto> medicalStaffList = medicalStaffService.findAll();
        return ResponseEntity.ok(medicalStaffList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalStaffDetailsDto> getMedicalStaffById(@PathVariable Long id) {
        MedicalStaffDetailsDto medicalStaff = medicalStaffService.findById(id);
        return ResponseEntity.ok(medicalStaff);
    }

    @PostMapping
    public ResponseEntity<MedicalStaffDetailsDto> createMedicalStaff(@Valid @RequestBody MedicalStaffRequestDto dto) {
        MedicalStaffDetailsDto createdStaff = medicalStaffService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStaff);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalStaffDetailsDto> updateMedicalStaff(
            @PathVariable Long id,
            @Valid @RequestBody MedicalStaffRequestDto dto) {
        MedicalStaffDetailsDto updatedStaff = medicalStaffService.update(id, dto);
        return ResponseEntity.ok(updatedStaff);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalStaff(@PathVariable Long id) {
        medicalStaffService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
