package org.example.medical_clinic_management_system.controller.specialization;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.specialization.SpecializationAssignmentRequestDTO;
import org.example.medical_clinic_management_system.dto.specialization.StaffSpecializationDetailsDTO;
import org.example.medical_clinic_management_system.service.specialization.MedicalStaffSpecializationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff/specializations")
@RequiredArgsConstructor
public class MedicalStaffSpecializationController
{

    private final MedicalStaffSpecializationService specializationService;

    @PostMapping("/assign")
    public ResponseEntity<StaffSpecializationDetailsDTO> assignSpecialization(
            @Valid @RequestBody SpecializationAssignmentRequestDTO requestDto) {

        StaffSpecializationDetailsDTO response = specializationService.assignSpecialization(requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{staffId}")
    public ResponseEntity<List<StaffSpecializationDetailsDTO>> getStaffSpecializations(
            @PathVariable Long staffId) {

        List<StaffSpecializationDetailsDTO> assignments = specializationService.getStaffSpecializations(staffId);


        return ResponseEntity.ok(assignments);
    }

    @DeleteMapping("/{staffId}/{specializationId}")
    public ResponseEntity<Void> removeSpecialization(
            @PathVariable Long staffId,
            @PathVariable Long specializationId) {

        specializationService.removeSpecialization(staffId, specializationId);


        return ResponseEntity.noContent().build();
    }





}
