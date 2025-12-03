package org.example.medical_clinic_management_system.controller.pharmacy;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.pharmacy.DrugDetailsDto;
import org.example.medical_clinic_management_system.dto.pharmacy.DrugRequestDto;
import org.example.medical_clinic_management_system.service.pharmacy.DrugService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugs")
@RequiredArgsConstructor
public class DrugController
{

    private final DrugService drugService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DrugDetailsDto> createDrug(@Valid @RequestBody DrugRequestDto requestDto) {
        DrugDetailsDto newDrug = drugService.createDrug(requestDto);
        return new ResponseEntity<>(newDrug, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
    public ResponseEntity<List<DrugDetailsDto>> getAllDrugs() {
        List<DrugDetailsDto> drugs = drugService.getAllDrugs();
        return ResponseEntity.ok(drugs);
    }

    @GetMapping("/{drugId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
    public ResponseEntity<DrugDetailsDto> getDrugById(@PathVariable Long drugId) {
        DrugDetailsDto drug = drugService.getDrugById(drugId);
        return ResponseEntity.ok(drug);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR') or hasRole('PATIENT')")
    public ResponseEntity<DrugDetailsDto> getDrugByAtcCode(@RequestParam String atcCode) {
        DrugDetailsDto drug = drugService.getDrugByAtcCode(atcCode);
        return ResponseEntity.ok(drug);
    }

    @PutMapping("/{drugId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DrugDetailsDto> updateDrug(
            @PathVariable Long drugId,
            @Valid @RequestBody DrugRequestDto requestDto) {

        DrugDetailsDto updatedDrug = drugService.updateDrug(drugId, requestDto);
        return ResponseEntity.ok(updatedDrug);
    }

    @DeleteMapping("/{drugId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDrug(@PathVariable Long drugId) {
        drugService.deleteDrug(drugId);
        return ResponseEntity.noContent().build();
    }



}
