package org.example.medical_clinic_management_system.controller.record;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.PatientCardDto;
import org.example.medical_clinic_management_system.service.record.PatientCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient-cards")
@RequiredArgsConstructor
public class PatientCardController
{

    private final PatientCardService service;

    @GetMapping
    public List<PatientCardDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PatientCardDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<PatientCardDto> create(@RequestBody PatientCardDto dto) {
        PatientCardDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public PatientCardDto update(@PathVariable Long id, @RequestBody PatientCardDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
