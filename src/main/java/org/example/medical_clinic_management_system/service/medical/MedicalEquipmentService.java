package org.example.medical_clinic_management_system.service.medical;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medical.MedicalEquipmentDto;
import org.example.medical_clinic_management_system.mapper.medical.MedicalEquipmentMapper;
import org.example.medical_clinic_management_system.model.medical.MedicalEquipment;
import org.example.medical_clinic_management_system.repository.medical.MedicalEquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalEquipmentService
{

    private final MedicalEquipmentRepository repository;
    private final MedicalEquipmentMapper mapper;

    public List<MedicalEquipmentDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public MedicalEquipmentDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
    }

    public MedicalEquipmentDto create(MedicalEquipmentDto dto) {
        MedicalEquipment entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public MedicalEquipmentDto update(Long id, MedicalEquipmentDto dto) {
        MedicalEquipment existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));
        MedicalEquipment updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
