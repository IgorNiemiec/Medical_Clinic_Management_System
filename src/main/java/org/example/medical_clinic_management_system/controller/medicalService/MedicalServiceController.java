package org.example.medical_clinic_management_system.controller.medicalService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medicalService.MedicalServiceDetailsDto;
import org.example.medical_clinic_management_system.dto.medicalService.MedicalServiceDto;
import org.example.medical_clinic_management_system.dto.medicalService.MedicalServiceRequestDto;
import org.example.medical_clinic_management_system.service.medicalService.MedicalServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-servises")
@RequiredArgsConstructor
public class MedicalServiceController
{
    private final MedicalServiceService medicalServiceService;

    @PostMapping
    public ResponseEntity<MedicalServiceDetailsDto> createMedicalService(@Valid @RequestBody MedicalServiceRequestDto requestDto) {
        MedicalServiceDetailsDto newService = medicalServiceService.createMedicalService(requestDto);
        return new ResponseEntity<>(newService, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MedicalServiceDetailsDto>> getAllMedicalServices() {
        List<MedicalServiceDetailsDto> services = medicalServiceService.getAllMedicalServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/search")
    public ResponseEntity<List<MedicalServiceDetailsDto>> searchMedicalServices(@RequestParam("title") String titleFragment) {
        List<MedicalServiceDetailsDto> services = medicalServiceService.searchMedicalServicesByTitle(titleFragment);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalServiceDetailsDto> getMedicalServiceById(@PathVariable Long id) {
        MedicalServiceDetailsDto service = medicalServiceService.getMedicalServiceById(id);
        return ResponseEntity.ok(service);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalServiceDetailsDto> updateMedicalService(@PathVariable Long id,
                                                                         @Valid @RequestBody MedicalServiceRequestDto requestDto) {
        MedicalServiceDetailsDto updatedService = medicalServiceService.updateMedicalService(id, requestDto);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalService(@PathVariable Long id) {
        medicalServiceService.deleteMedicalService(id);
        return ResponseEntity.noContent().build();
    }





}
