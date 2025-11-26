package org.example.medical_clinic_management_system.service.pharmacy;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.pharmacy.DrugDetailsDto;
import org.example.medical_clinic_management_system.dto.pharmacy.DrugRequestDto;
import org.example.medical_clinic_management_system.mapper.pharmacy.DrugMapper;
import org.example.medical_clinic_management_system.model.pharmacy.Drug;
import org.example.medical_clinic_management_system.repository.pharmacy.DrugRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrugService
{

    private final DrugRepository drugRepository;
    private final DrugMapper drugMapper;


    @Transactional
    public DrugDetailsDto createDrug(DrugRequestDto requestDto) {



        if (drugRepository.findByAtcCode(requestDto.getAtcCode()).isPresent()) {
            throw new RuntimeException("Drug with ATC Code " + requestDto.getAtcCode() + " already exists.");
        }

        if (drugRepository.findByGtinNumber(requestDto.getGtinNumber()).isPresent()) {
            throw new RuntimeException("Drug with GTIN Number " + requestDto.getGtinNumber() + " already exists.");
        }

        Drug drug = drugMapper.toEntity(requestDto);
        Drug savedDrug = drugRepository.save(drug);

        return drugMapper.toDetailsDto(savedDrug);
    }


    @Transactional
    public DrugDetailsDto getDrugById(Long drugId) {
        Drug drug = drugRepository.findById(drugId)
                .orElseThrow(() -> new RuntimeException("Drug not found with ID: " + drugId));

        return drugMapper.toDetailsDto(drug);
    }

    @Transactional
    public List<DrugDetailsDto> getAllDrugs() {
        List<Drug> drugs = drugRepository.findAll();

        return drugMapper.toDetailsDtoList(drugs);
    }


    @Transactional
    public DrugDetailsDto updateDrug(Long drugId, DrugRequestDto requestDto) {
        Drug existingDrug = drugRepository.findById(drugId)
                .orElseThrow(() -> new RuntimeException("Drug not found with ID: " + drugId));


        Optional<Drug> drugWithSameAtc = drugRepository.findByAtcCode(requestDto.getAtcCode());
        if (drugWithSameAtc.isPresent() && !drugWithSameAtc.get().getId().equals(drugId)) {
            throw new RuntimeException("Another drug already has ATC Code: " + requestDto.getAtcCode());
        }

        Optional<Drug> drugWithSameGtin = drugRepository.findByGtinNumber(requestDto.getGtinNumber());
        if (drugWithSameGtin.isPresent() && !drugWithSameGtin.get().getId().equals(drugId)) {
            throw new RuntimeException("Another drug already has GTIN Number: " + requestDto.getGtinNumber());
        }

        drugMapper.updateEntityFromDto(requestDto, existingDrug);

        Drug updatedDrug = drugRepository.save(existingDrug);

        return drugMapper.toDetailsDto(updatedDrug);
    }

    @Transactional
    public void deleteDrug(Long drugId) {
        Drug drug = drugRepository.findById(drugId)
                .orElseThrow(() -> new RuntimeException("Drug not found with ID: " + drugId));
        drugRepository.delete(drug);
    }


}
