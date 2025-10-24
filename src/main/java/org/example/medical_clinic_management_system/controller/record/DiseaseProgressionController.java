package org.example.medical_clinic_management_system.controller.record;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.DiseaseProgressionDto;
import org.example.medical_clinic_management_system.service.record.DiseaseProgressionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disease-progressions")
@RequiredArgsConstructor
public class DiseaseProgressionController
{

    private final DiseaseProgressionService service;

    @GetMapping
    public List<DiseaseProgressionDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DiseaseProgressionDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<DiseaseProgressionDto> create(@RequestBody DiseaseProgressionDto dto) {
        DiseaseProgressionDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public DiseaseProgressionDto update(@PathVariable Long id, @RequestBody DiseaseProgressionDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
