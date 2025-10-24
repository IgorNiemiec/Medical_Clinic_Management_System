package org.example.medical_clinic_management_system.controller.visit;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.MedicalNoteDto;
import org.example.medical_clinic_management_system.service.visit.MedicalNoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-notes")
@RequiredArgsConstructor
public class MedicalNoteController
{

    private final MedicalNoteService medicalNoteService;

    @GetMapping
    public ResponseEntity<List<MedicalNoteDto>> getAll() {
        return ResponseEntity.ok(medicalNoteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalNoteDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(medicalNoteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<MedicalNoteDto> create(@RequestBody MedicalNoteDto dto) {
        return ResponseEntity.ok(medicalNoteService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalNoteDto> update(@PathVariable Long id, @RequestBody MedicalNoteDto dto) {
        return ResponseEntity.ok(medicalNoteService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        medicalNoteService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
