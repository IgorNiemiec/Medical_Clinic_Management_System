package org.example.medical_clinic_management_system.controller.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.ReceptionistDto;
import org.example.medical_clinic_management_system.service.person.ReceptionistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receptionists")
@RequiredArgsConstructor
public class ReceptionistController
{

    private final ReceptionistService service;

    @GetMapping
    public List<ReceptionistDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ReceptionistDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<ReceptionistDto> create(@RequestBody ReceptionistDto dto) {
        ReceptionistDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ReceptionistDto update(@PathVariable Long id, @RequestBody ReceptionistDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
