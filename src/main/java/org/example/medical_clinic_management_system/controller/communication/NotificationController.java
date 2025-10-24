package org.example.medical_clinic_management_system.controller.communication;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.communication.NotificationDto;
import org.example.medical_clinic_management_system.service.communication.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController
{

    private final NotificationService notificationService;

    @GetMapping
    public List<NotificationDto> getAll() {
        return notificationService.getAll();
    }

    @GetMapping("/{id}")
    public NotificationDto getById(@PathVariable Long id) {
        return notificationService.getById(id);
    }

    @PostMapping
    public ResponseEntity<NotificationDto> create(@RequestBody NotificationDto dto) {
        NotificationDto saved = notificationService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public NotificationDto update(@PathVariable Long id, @RequestBody NotificationDto dto) {
        return notificationService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notificationService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
