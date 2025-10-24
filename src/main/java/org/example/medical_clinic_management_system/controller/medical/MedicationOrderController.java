package org.example.medical_clinic_management_system.controller.medical;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medical.MedicationOrderDto;
import org.example.medical_clinic_management_system.service.medical.MedicationOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medication-orders")
@RequiredArgsConstructor
public class MedicationOrderController
{

    private final MedicationOrderService service;

    @GetMapping
    public List<MedicationOrderDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MedicationOrderDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<MedicationOrderDto> create(@RequestBody MedicationOrderDto dto) {
        MedicationOrderDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public MedicationOrderDto update(@PathVariable Long id, @RequestBody MedicationOrderDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
