package org.example.medical_clinic_management_system.controller.schedule;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.schedule.EmployeeScheduleDto;
import org.example.medical_clinic_management_system.service.schedule.EmployeeScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-schedules")
@RequiredArgsConstructor
public class EmployeeScheduleController
{

    private final EmployeeScheduleService service;

    @GetMapping
    public List<EmployeeScheduleDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeScheduleDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<EmployeeScheduleDto> create(@RequestBody EmployeeScheduleDto dto) {
        EmployeeScheduleDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public EmployeeScheduleDto update(@PathVariable Long id, @RequestBody EmployeeScheduleDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
