package org.example.medical_clinic_management_system.controller.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.PatientDto;
import org.example.medical_clinic_management_system.service.person.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController
{

    private final PatientService service;

    @GetMapping
    public List<PatientDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PatientDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<PatientDto> create(@RequestBody PatientDto dto) {
        PatientDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public PatientDto update(@PathVariable Long id, @RequestBody PatientDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
