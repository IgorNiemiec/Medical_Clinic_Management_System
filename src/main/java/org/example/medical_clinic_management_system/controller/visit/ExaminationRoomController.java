package org.example.medical_clinic_management_system.controller.visit;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.ExaminationRoomDto;
import org.example.medical_clinic_management_system.service.visit.ExaminationRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examination-rooms")
@RequiredArgsConstructor
public class ExaminationRoomController
{

    private final ExaminationRoomService examinationRoomService;

    @GetMapping
    public ResponseEntity<List<ExaminationRoomDto>> getAll() {
        return ResponseEntity.ok(examinationRoomService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExaminationRoomDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(examinationRoomService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ExaminationRoomDto> create(@RequestBody ExaminationRoomDto dto) {
        return ResponseEntity.ok(examinationRoomService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExaminationRoomDto> update(@PathVariable Long id, @RequestBody ExaminationRoomDto dto) {
        return ResponseEntity.ok(examinationRoomService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        examinationRoomService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
