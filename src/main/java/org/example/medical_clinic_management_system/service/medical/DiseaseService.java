package org.example.medical_clinic_management_system.service.medical;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medical.DiseaseDto;
import org.example.medical_clinic_management_system.mapper.medical.DiseaseMapper;
import org.example.medical_clinic_management_system.model.medical.Disease;
import org.example.medical_clinic_management_system.repository.medical.DiseaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiseaseService
{

    private final DiseaseRepository diseaseRepository;
    private final DiseaseMapper mapper;

    public List<DiseaseDto> getAll() {
        return diseaseRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public DiseaseDto getById(Long id) {
        return diseaseRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Disease not found"));
    }

    public DiseaseDto create(DiseaseDto dto) {
        Disease entity = mapper.toEntity(dto);
        return mapper.toDto(diseaseRepository.save(entity));
    }

    public DiseaseDto update(Long id, DiseaseDto dto) {
        Disease existing = diseaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disease not found"));
        Disease updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(diseaseRepository.save(updated));
    }

    public void delete(Long id) {
        diseaseRepository.deleteById(id);
    }


}
