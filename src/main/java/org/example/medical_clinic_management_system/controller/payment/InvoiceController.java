package org.example.medical_clinic_management_system.controller.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.payment.InvoiceDetailsDto;
import org.example.medical_clinic_management_system.dto.payment.InvoiceRequestDto;
import org.example.medical_clinic_management_system.model.payment.Invoice;
import org.example.medical_clinic_management_system.service.payment.InvoiceService;
import org.example.medical_clinic_management_system.xml.dto.InvoiceXmlDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController
{

    private final InvoiceService invoiceService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<InvoiceDetailsDto> generateInvoice(@Valid @RequestBody InvoiceRequestDto requestDto) {
        InvoiceDetailsDto newInvoice = invoiceService.generateInvoice(requestDto);
        return new ResponseEntity<>(newInvoice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<InvoiceDetailsDto> getInvoiceById(@PathVariable Long id) {
        InvoiceDetailsDto invoice = invoiceService.getInvoiceById(id);
        return ResponseEntity.ok(invoice);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<List<InvoiceDetailsDto>> getAllInvoices() {
        List<InvoiceDetailsDto> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<InvoiceDetailsDto> updateInvoiceStatus(
            @PathVariable Long id,
            @RequestParam Invoice.InvoiceStatus newStatus) {

        InvoiceDetailsDto updatedInvoice = invoiceService.updateInvoiceStatus(id, newStatus);
        return ResponseEntity.ok(updatedInvoice);
    }


    @GetMapping("/{id}/export-xml")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<String> exportInvoiceXml(@PathVariable Long id) {
        String xmlContent = invoiceService.exportInvoiceToXml(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        // Zmieniona nazwa pliku na angielski
        headers.setContentDispositionFormData("attachment", "invoice_" + id + ".xml");

        return new ResponseEntity<>(xmlContent, headers, HttpStatus.OK);
    }

    @PostMapping(value = "/import-xml", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<InvoiceXmlDto> importInvoiceXml(@RequestBody String xmlContent) {
        InvoiceXmlDto importedDto = invoiceService.importInvoiceFromXml(xmlContent);
        return ResponseEntity.ok(importedDto);
    }






}
