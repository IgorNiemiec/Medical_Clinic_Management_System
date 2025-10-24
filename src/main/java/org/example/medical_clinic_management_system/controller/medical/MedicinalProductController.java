package org.example.medical_clinic_management_system.controller.medical;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medical.MedicinalProductDto;
import org.example.medical_clinic_management_system.service.medical.MedicinalProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicinal-products")
@RequiredArgsConstructor
public class MedicinalProductController
{

    private final MedicinalProductService service;

    @GetMapping
    public List<MedicinalProductDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MedicinalProductDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<MedicinalProductDto> create(@RequestBody MedicinalProductDto dto) {
        MedicinalProductDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public MedicinalProductDto update(@PathVariable Long id, @RequestBody MedicinalProductDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
