package org.example.medical_clinic_management_system.controller.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.payment.InvoiceDetailsDto;
import org.example.medical_clinic_management_system.dto.payment.InvoiceRequestDto;
import org.example.medical_clinic_management_system.model.payment.Invoice;
import org.example.medical_clinic_management_system.service.payment.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController
{

    private final InvoiceService invoiceService;


    @PostMapping
    public ResponseEntity<InvoiceDetailsDto> generateInvoice(@Valid @RequestBody InvoiceRequestDto requestDto) {
        InvoiceDetailsDto newInvoice = invoiceService.generateInvoice(requestDto);
        return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDetailsDto> getInvoiceById(@PathVariable Long id) {
        InvoiceDetailsDto invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDetailsDto>> getAllInvoices() {
        List<InvoiceDetailsDto> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<InvoiceDetailsDto> updateInvoiceStatus(
            @PathVariable Long id,
            @RequestParam Invoice.InvoiceStatus newStatus) {

        InvoiceDetailsDto updatedInvoice = invoiceService.updateInvoiceStatus(id, newStatus);
        return ResponseEntity.ok(updatedInvoice);
    }





}
