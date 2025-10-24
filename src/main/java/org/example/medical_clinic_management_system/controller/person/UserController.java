package org.example.medical_clinic_management_system.controller.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.UserDto;
import org.example.medical_clinic_management_system.service.person.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController
{

    private final UserService service;

    @GetMapping
    public List<UserDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto) {
        UserDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
