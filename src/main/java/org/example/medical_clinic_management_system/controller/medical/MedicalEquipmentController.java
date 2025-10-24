package org.example.medical_clinic_management_system.controller.medical;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medical.MedicalEquipmentDto;
import org.example.medical_clinic_management_system.service.medical.MedicalEquipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class MedicalEquipmentController
{

    private final MedicalEquipmentService service;

    @GetMapping
    public List<MedicalEquipmentDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MedicalEquipmentDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<MedicalEquipmentDto> create(@RequestBody MedicalEquipmentDto dto) {
        MedicalEquipmentDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public MedicalEquipmentDto update(@PathVariable Long id, @RequestBody MedicalEquipmentDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
