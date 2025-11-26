package org.example.medical_clinic_management_system.controller.schedule;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.schedule.ScheduleDto;
import org.example.medical_clinic_management_system.service.schedule.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController
{
    private final ScheduleService service;

    @GetMapping
    public List<ScheduleDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ScheduleDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<ScheduleDto> create(@Valid @RequestBody ScheduleDto dto) {
        ScheduleDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ScheduleDto update(@PathVariable Long id, @RequestBody ScheduleDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
