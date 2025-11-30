package org.example.medical_clinic_management_system.controller.record;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.ICDCodeDto;
import org.example.medical_clinic_management_system.service.record.ICDCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/icd-codes")
@RequiredArgsConstructor
public class ICDCodeController
{

    private final ICDCodeService icdCodeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ICDCodeDto> createICDCode(@Valid @RequestBody ICDCodeDto dto) {
        ICDCodeDto newIcdCode = icdCodeService.createICDCode(dto);
        return new ResponseEntity<>(newIcdCode, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
    public ResponseEntity<List<ICDCodeDto>> getAllICDCodes() {
        List<ICDCodeDto> icdCodes = icdCodeService.getAllICDCodes();
        return ResponseEntity.ok(icdCodes);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
    public ResponseEntity<ICDCodeDto> getICDCodeById(@PathVariable Long id) {
        ICDCodeDto icdCode = icdCodeService.getICDCodeById(id);
        return ResponseEntity.ok(icdCode);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
    public ResponseEntity<List<ICDCodeDto>> searchICDCodes(@RequestParam String query) {
        List<ICDCodeDto> icdCodes = icdCodeService.searchICDCodes(query);
        return ResponseEntity.ok(icdCodes);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST') or hasRole('DOCTOR')")
    public ResponseEntity<ICDCodeDto> updateICDCode(@PathVariable Long id, @Valid @RequestBody ICDCodeDto dto) {
        ICDCodeDto updatedIcdCode = icdCodeService.updateICDCode(id, dto);
        return ResponseEntity.ok(updatedIcdCode);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteICDCode(@PathVariable Long id) {
        icdCodeService.deleteICDCode(id);
        return ResponseEntity.noContent().build();
    }


}
