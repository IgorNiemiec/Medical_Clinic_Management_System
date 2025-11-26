package org.example.medical_clinic_management_system.service.record;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.ICDCodeDto;
import org.example.medical_clinic_management_system.mapper.record.ICDCodeMapper;
import org.example.medical_clinic_management_system.model.record.ICDCode;
import org.example.medical_clinic_management_system.repository.record.ICDCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ICDCodeService
{

    private final ICDCodeRepository icdCodeRepository;
    private final ICDCodeMapper icdCodeMapper;

    @Transactional
    public ICDCodeDto createICDCode(ICDCodeDto dto) {
        if (icdCodeRepository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Kod ICD: " + dto.getCode() + " już istnieje w bazie.");
        }

        ICDCode entity = icdCodeMapper.toEntity(dto);
        ICDCode savedEntity = icdCodeRepository.save(entity);
        return icdCodeMapper.toDto(savedEntity);
    }

    @Transactional
    public ICDCodeDto getICDCodeById(Long id) {
        ICDCode entity = icdCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kod ICD nie został znaleziony o ID: " + id));
        return icdCodeMapper.toDto(entity);
    }

    @Transactional
    public List<ICDCodeDto> searchICDCodes(String query) {
        List<ICDCode> entities = icdCodeRepository.findByNamePlContainingIgnoreCaseOrNameEnContainingIgnoreCase(query, query);
        return icdCodeMapper.toDtoList(entities);
    }

    @Transactional
    public List<ICDCodeDto> getAllICDCodes() {
        List<ICDCode> entities = icdCodeRepository.findAll();
        return icdCodeMapper.toDtoList(entities);
    }

    @Transactional
    public ICDCodeDto updateICDCode(Long id, ICDCodeDto dto) {
        ICDCode entity = icdCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kod ICD nie został znaleziony o ID: " + id));


        if (!entity.getCode().equals(dto.getCode()) && icdCodeRepository.existsByCode(dto.getCode())) {
            throw new RuntimeException("Kod ICD: " + dto.getCode() + " jest już zajęty.");
        }

        icdCodeMapper.updateEntity(entity, dto);
        ICDCode updatedEntity = icdCodeRepository.save(entity);
        return icdCodeMapper.toDto(updatedEntity);
    }

    @Transactional
    public void deleteICDCode(Long id) {
        if (!icdCodeRepository.existsById(id)) {
            throw new RuntimeException("Kod ICD nie został znaleziony o ID: " + id);
        }
        icdCodeRepository.deleteById(id);
    }




}
