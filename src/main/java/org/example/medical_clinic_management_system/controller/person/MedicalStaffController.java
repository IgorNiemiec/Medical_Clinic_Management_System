package org.example.medical_clinic_management_system.controller.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffDto;
import org.example.medical_clinic_management_system.service.person.MedicalStaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-staff")
@RequiredArgsConstructor
public class MedicalStaffController
{

    private final MedicalStaffService service;

    @GetMapping
    public List<MedicalStaffDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MedicalStaffDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<MedicalStaffDto> create(@RequestBody MedicalStaffDto dto) {
        MedicalStaffDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public MedicalStaffDto update(@PathVariable Long id, @RequestBody MedicalStaffDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
