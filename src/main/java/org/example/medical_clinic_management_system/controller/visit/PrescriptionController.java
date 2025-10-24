package org.example.medical_clinic_management_system.controller.visit;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.PrescriptionDto;
import org.example.medical_clinic_management_system.service.visit.PrescriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController
{

    private final PrescriptionService prescriptionService;

    @GetMapping
    public ResponseEntity<List<PrescriptionDto>> getAll() {
        return ResponseEntity.ok(prescriptionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(prescriptionService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PrescriptionDto> create(@RequestBody PrescriptionDto dto) {
        return ResponseEntity.ok(prescriptionService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrescriptionDto> update(@PathVariable Long id, @RequestBody PrescriptionDto dto) {
        return ResponseEntity.ok(prescriptionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        prescriptionService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
