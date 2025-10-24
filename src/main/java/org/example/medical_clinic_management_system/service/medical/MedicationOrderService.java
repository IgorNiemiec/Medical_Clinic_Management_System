package org.example.medical_clinic_management_system.service.medical;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medical.MedicationOrderDto;
import org.example.medical_clinic_management_system.mapper.medical.MedicationOrderMapper;
import org.example.medical_clinic_management_system.model.medical.MedicationOrder;
import org.example.medical_clinic_management_system.model.medical.MedicinalProduct;
import org.example.medical_clinic_management_system.repository.medical.MedicationOrderRepository;
import org.example.medical_clinic_management_system.repository.medical.MedicinalProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicationOrderService
{

    private final MedicationOrderRepository repository;
    private final MedicinalProductRepository productRepository;
    private final MedicationOrderMapper mapper;

    public List<MedicationOrderDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public MedicationOrderDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Medication order not found"));
    }

    public MedicationOrderDto create(MedicationOrderDto dto) {
        MedicinalProduct product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        MedicationOrder entity = mapper.toEntity(dto, product);
        return mapper.toDto(repository.save(entity));
    }

    public MedicationOrderDto update(Long id, MedicationOrderDto dto) {
        MedicationOrder existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medication order not found"));
        MedicinalProduct product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        MedicationOrder updated = mapper.toEntity(dto, product);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
