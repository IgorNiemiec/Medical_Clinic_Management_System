package org.example.medical_clinic_management_system.service.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.SpecializationDto;
import org.example.medical_clinic_management_system.mapper.person.SpecializationMapper;
import org.example.medical_clinic_management_system.model.person.Specialization;
import org.example.medical_clinic_management_system.repository.person.SpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecializationService
{

    private final SpecializationRepository repository;
    private final SpecializationMapper mapper;

    public List<SpecializationDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public SpecializationDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Specialization not found"));
    }

    public SpecializationDto create(SpecializationDto dto) {
        Specialization entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public SpecializationDto update(Long id, SpecializationDto dto) {
        Specialization existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialization not found"));
        Specialization updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
