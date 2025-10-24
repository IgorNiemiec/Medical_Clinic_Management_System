package org.example.medical_clinic_management_system.controller.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.SpecializationDto;
import org.example.medical_clinic_management_system.service.person.SpecializationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specializations")
@RequiredArgsConstructor
public class SpecializationController
{
    private final SpecializationService service;

    @GetMapping
    public List<SpecializationDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SpecializationDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<SpecializationDto> create(@RequestBody SpecializationDto dto) {
        SpecializationDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public SpecializationDto update(@PathVariable Long id, @RequestBody SpecializationDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
