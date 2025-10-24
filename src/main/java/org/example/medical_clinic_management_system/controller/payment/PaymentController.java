package org.example.medical_clinic_management_system.controller.payment;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.payment.PaymentDto;
import org.example.medical_clinic_management_system.service.payment.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController
{

    private final PaymentService service;

    @GetMapping
    public List<PaymentDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PaymentDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentDto dto) {
        PaymentDto saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public PaymentDto update(@PathVariable Long id, @RequestBody PaymentDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
