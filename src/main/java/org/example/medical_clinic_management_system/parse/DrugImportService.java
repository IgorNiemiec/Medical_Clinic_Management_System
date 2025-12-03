package org.example.medical_clinic_management_system.parse;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.pharmacy.DrugRequestDto;
import org.example.medical_clinic_management_system.model.pharmacy.Drug;
import org.example.medical_clinic_management_system.repository.pharmacy.DrugRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrugImportService
{

    private static final Logger logger = LoggerFactory.getLogger(DrugImportService.class);
    private final DrugRepository drugRepository;


    public int importDrugs(List<DrugRequestDto> drugsToImport) {
        int importedCount = 0;
        logger.info("Rozpoczęto import {} leków do bazy danych.", drugsToImport.size());

        for (DrugRequestDto dto : drugsToImport)
        {
            Optional<Drug> existingDrug = drugRepository.findByAtcCode(dto.getAtcCode());

            if (existingDrug.isPresent())
            {
                Drug drug = existingDrug.get();
                updateDrugFromDto(drug, dto);
                drugRepository.save(drug);
                logger.debug("Zaktualizowano istniejący lek: {}", dto.getProductName());
            } else {
                Drug newDrug = mapDtoToDrug(dto);
                drugRepository.save(newDrug);
                logger.debug("Utworzono nowy lek: {}", dto.getProductName());
            }

            importedCount++;
        }

        logger.info("Zakończono import. Pomyślnie przetworzono {} rekordów.", importedCount);
        return importedCount;
    }

    private Drug mapDtoToDrug(DrugRequestDto dto) {
        return Drug.builder()
                .productName(dto.getProductName())
                .commonName(dto.getCommonName())
                .form(dto.getForm())
                .atcCode(dto.getAtcCode())
                .gtinNumber(dto.getGtinNumber())
                .build();
    }

    private void updateDrugFromDto(Drug drug, DrugRequestDto dto) {
        drug.setProductName(dto.getProductName());
        drug.setCommonName(dto.getCommonName());
        drug.setForm(dto.getForm());
    }


}
