package org.example.medical_clinic_management_system.controller.visit;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.ExaminationRoomDetailsDto;
import org.example.medical_clinic_management_system.dto.visit.ExaminationRoomDto;
import org.example.medical_clinic_management_system.dto.visit.ExaminationRoomRequestDto;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;
import org.example.medical_clinic_management_system.service.visit.ExaminationRoomService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ExaminationRoomDetailsDto>> getAllRooms() {
        List<ExaminationRoomDetailsDto> rooms = examinationRoomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/status")
    public ResponseEntity<List<ExaminationRoomDetailsDto>> getRoomsByStatus(@RequestParam ExaminationRoom.ExaminationRoomStatus status) {
        List<ExaminationRoomDetailsDto> rooms = examinationRoomService.getRoomsByStatus(status);
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExaminationRoomDetailsDto> getRoomById(@PathVariable Long id) {
        ExaminationRoomDetailsDto room = examinationRoomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }

    @PostMapping
    public ResponseEntity<ExaminationRoomDetailsDto> createRoom(@Valid @RequestBody ExaminationRoomRequestDto dto) {
        ExaminationRoomDetailsDto newRoom = examinationRoomService.createRoom(dto);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExaminationRoomDetailsDto> updateRoom(@PathVariable Long id, @Valid @RequestBody ExaminationRoomRequestDto dto) {
        ExaminationRoomDetailsDto updatedRoom = examinationRoomService.updateRoom(id, dto);
        return ResponseEntity.ok(updatedRoom);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ExaminationRoomDetailsDto> updateRoomStatus(@PathVariable Long id, @RequestParam ExaminationRoom.ExaminationRoomStatus newStatus) {
        ExaminationRoomDetailsDto updatedRoom = examinationRoomService.updateRoomStatus(id, newStatus);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        examinationRoomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

}
