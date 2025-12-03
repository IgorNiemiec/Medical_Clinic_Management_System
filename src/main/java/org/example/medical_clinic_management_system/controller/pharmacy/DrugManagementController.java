package org.example.medical_clinic_management_system.controller.pharmacy;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.pharmacy.DrugRequestDto;
import org.example.medical_clinic_management_system.parse.DrugImportService;
import org.example.medical_clinic_management_system.parse.RplApiImporterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/drugs")
@RequiredArgsConstructor
public class DrugManagementController
{

    private final RplApiImporterService rplApiImporterService;
    private final DrugImportService drugImportService;


    @PostMapping("/import-rpl")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<Map<String, Object>> importExternalDrugs(@RequestParam String query) {

        List<DrugRequestDto> drugsToImport = rplApiImporterService.fetchAndParseDrugs(query);

        if (drugsToImport.isEmpty()) {
            return ResponseEntity.ok(Map.of("importedCount", 0, "status", 200, "message", "Nie znaleziono leków lub wystąpił błąd komunikacji z API RPL."));
        }

        int importedCount = drugImportService.importDrugs(drugsToImport);

        return ResponseEntity.ok(Map.of("importedCount", importedCount, "status", 200, "message", "Pomyślnie zaimportowano " + importedCount + " rekordów leków."));
    }



}
