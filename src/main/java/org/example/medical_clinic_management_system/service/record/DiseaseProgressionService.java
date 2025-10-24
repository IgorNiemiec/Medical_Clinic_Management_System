package org.example.medical_clinic_management_system.service.record;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.DiseaseProgressionDto;
import org.example.medical_clinic_management_system.mapper.record.DiseaseProgressionMapper;
import org.example.medical_clinic_management_system.model.medical.Disease;
import org.example.medical_clinic_management_system.model.record.DiseaseProgression;
import org.example.medical_clinic_management_system.model.record.PatientCard;
import org.example.medical_clinic_management_system.repository.medical.DiseaseRepository;
import org.example.medical_clinic_management_system.repository.record.DiseaseProgressionRepository;
import org.example.medical_clinic_management_system.repository.record.PatientCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiseaseProgressionService
{

    private final DiseaseProgressionRepository repository;
    private final PatientCardRepository cardRepository;
    private final DiseaseRepository diseaseRepository;
    private final DiseaseProgressionMapper mapper;

    public List<DiseaseProgressionDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public DiseaseProgressionDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Disease progression not found"));
    }

    public DiseaseProgressionDto create(DiseaseProgressionDto dto) {
        PatientCard card = cardRepository.findById(dto.getPatientCardId())
                .orElseThrow(() -> new RuntimeException("Patient card not found"));
        Disease disease = diseaseRepository.findById(dto.getDiseaseId())
                .orElseThrow(() -> new RuntimeException("Disease not found"));
        DiseaseProgression entity = mapper.toEntity(dto, card, disease);
        return mapper.toDto(repository.save(entity));
    }

    public DiseaseProgressionDto update(Long id, DiseaseProgressionDto dto) {
        DiseaseProgression existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disease progression not found"));
        PatientCard card = cardRepository.findById(dto.getPatientCardId())
                .orElseThrow(() -> new RuntimeException("Patient card not found"));
        Disease disease = diseaseRepository.findById(dto.getDiseaseId())
                .orElseThrow(() -> new RuntimeException("Disease not found"));
        DiseaseProgression updated = mapper.toEntity(dto, card, disease);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
