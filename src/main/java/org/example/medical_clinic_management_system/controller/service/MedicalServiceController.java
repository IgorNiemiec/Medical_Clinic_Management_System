package org.example.medical_clinic_management_system.controller.service;


import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.service.MedicalServiceDto;
import org.example.medical_clinic_management_system.service.service.MedicalServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-servises")
@RequiredArgsConstructor
public class MedicalServiceController
{



        private final MedicalServiceService medicalServiceService;

        @GetMapping
        public ResponseEntity<List<MedicalServiceDto>> getAll() {
        return ResponseEntity.ok(medicalServiceService.getAll());
    }

        @GetMapping("/{id}")
        public ResponseEntity<MedicalServiceDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalServiceService.getById(id));
    }

        @PostMapping
        public ResponseEntity<MedicalServiceDto> create(@RequestBody MedicalServiceDto dto) {
        return ResponseEntity.ok(medicalServiceService.create(dto));
    }

        @PutMapping("/{id}")
        public ResponseEntity<MedicalServiceDto> update(@PathVariable Long id, @RequestBody MedicalServiceDto dto) {
        return ResponseEntity.ok(medicalServiceService.update(id, dto));
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicalServiceService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
