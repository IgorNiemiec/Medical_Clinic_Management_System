package org.example.medical_clinic_management_system.controller.schedule;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.schedule.WaitingListDto;
import org.example.medical_clinic_management_system.service.schedule.WaitingListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waiting-list")
@RequiredArgsConstructor
public class WaitingListController
{

    private final WaitingListService service;

    @GetMapping
    public List<WaitingListDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public WaitingListDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<WaitingListDto> create(@RequestBody WaitingListDto dto) {
        WaitingListDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public WaitingListDto update(@PathVariable Long id, @RequestBody WaitingListDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
