package org.example.medical_clinic_management_system.controller.medical;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medical.DiseaseDto;
import org.example.medical_clinic_management_system.service.medical.DiseaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diseases")
@RequiredArgsConstructor
public class DiseaseController
{

    private final DiseaseService diseaseService;

    @GetMapping
    public List<DiseaseDto> getAll() {
        return diseaseService.getAll();
    }

    @GetMapping("/{id}")
    public DiseaseDto getById(@PathVariable Long id) {
        return diseaseService.getById(id);
    }

    @PostMapping
    public ResponseEntity<DiseaseDto> create(@RequestBody DiseaseDto dto) {
        DiseaseDto saved = diseaseService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public DiseaseDto update(@PathVariable Long id, @RequestBody DiseaseDto dto) {
        return diseaseService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        diseaseService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
