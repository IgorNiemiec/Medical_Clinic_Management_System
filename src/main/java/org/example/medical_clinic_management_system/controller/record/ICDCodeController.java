package org.example.medical_clinic_management_system.controller.record;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.ICDCodeDto;
import org.example.medical_clinic_management_system.service.record.ICDCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/icd-codes")
@RequiredArgsConstructor
public class ICDCodeController
{

    private final ICDCodeService icdCodeService;

    @PostMapping
    public ResponseEntity<ICDCodeDto> createICDCode(@Valid @RequestBody ICDCodeDto dto) {
        ICDCodeDto newIcdCode = icdCodeService.createICDCode(dto);
        return new ResponseEntity<>(newIcdCode, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ICDCodeDto>> getAllICDCodes() {
        List<ICDCodeDto> icdCodes = icdCodeService.getAllICDCodes();
        return ResponseEntity.ok(icdCodes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ICDCodeDto> getICDCodeById(@PathVariable Long id) {
        ICDCodeDto icdCode = icdCodeService.getICDCodeById(id);
        return ResponseEntity.ok(icdCode);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ICDCodeDto>> searchICDCodes(@RequestParam String query) {
        List<ICDCodeDto> icdCodes = icdCodeService.searchICDCodes(query);
        return ResponseEntity.ok(icdCodes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ICDCodeDto> updateICDCode(@PathVariable Long id, @Valid @RequestBody ICDCodeDto dto) {
        ICDCodeDto updatedIcdCode = icdCodeService.updateICDCode(id, dto);
        return ResponseEntity.ok(updatedIcdCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteICDCode(@PathVariable Long id) {
        icdCodeService.deleteICDCode(id);
        return ResponseEntity.noContent().build();
    }


}
