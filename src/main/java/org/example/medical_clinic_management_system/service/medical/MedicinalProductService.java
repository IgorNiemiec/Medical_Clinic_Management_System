package org.example.medical_clinic_management_system.service.medical;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medical.MedicinalProductDto;
import org.example.medical_clinic_management_system.mapper.medical.MedicinalProductMapper;
import org.example.medical_clinic_management_system.model.medical.MedicinalProduct;
import org.example.medical_clinic_management_system.repository.medical.MedicinalProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicinalProductService
{

    private final MedicinalProductRepository repository;
    private final MedicinalProductMapper mapper;

    public List<MedicinalProductDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public MedicinalProductDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public MedicinalProductDto create(MedicinalProductDto dto) {
        MedicinalProduct entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public MedicinalProductDto update(Long id, MedicinalProductDto dto) {
        MedicinalProduct existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        MedicinalProduct updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
