package org.example.medical_clinic_management_system.controller.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.payment.PaymentDetailsDto;
import org.example.medical_clinic_management_system.dto.payment.PaymentDto;
import org.example.medical_clinic_management_system.dto.payment.PaymentRequestDto;
import org.example.medical_clinic_management_system.service.payment.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController
{

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDetailsDto> registerPayment(@Valid @RequestBody PaymentRequestDto requestDto) {
        PaymentDetailsDto newPayment = paymentService.registerPayment(requestDto);
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDetailsDto> getPaymentById(@PathVariable Long id) {
        PaymentDetailsDto payment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(payment);
    }

    @GetMapping("/invoice/{invoiceId}")
    public ResponseEntity<List<PaymentDetailsDto>> getPaymentsByInvoiceId(@PathVariable Long invoiceId) {
        List<PaymentDetailsDto> payments = paymentService.getPaymentsByInvoiceId(invoiceId);
        return ResponseEntity.ok(payments);
    }

    @GetMapping("/total-paid/{invoiceId}")
    public ResponseEntity<BigDecimal> calculateTotalPaid(@PathVariable Long invoiceId) {
        BigDecimal totalPaid = paymentService.calculateTotalPaid(invoiceId);
        return ResponseEntity.ok(totalPaid);
    }

    @GetMapping("/balance/{invoiceId}")
    public ResponseEntity<BigDecimal> calculateOutstandingBalance(@PathVariable Long invoiceId) {
        BigDecimal balance = paymentService.calculateOutstandingBalance(invoiceId);
        return ResponseEntity.ok(balance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }



}
