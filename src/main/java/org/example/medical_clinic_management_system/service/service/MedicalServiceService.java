package org.example.medical_clinic_management_system.service.service;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.service.MedicalServiceDto;
import org.example.medical_clinic_management_system.mapper.service.MedicalServiceMapper;
import org.example.medical_clinic_management_system.model.service.MedicalService;
import org.example.medical_clinic_management_system.repository.service.MedicalServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalServiceService
{

    private final MedicalServiceRepository medicalServiseRepository;
    private final MedicalServiceMapper medicalServiseMapper;

    public List<MedicalServiceDto> getAll() {
        return medicalServiseRepository.findAll()
                .stream()
                .map(medicalServiseMapper::toDto)
                .collect(Collectors.toList());
    }

    public MedicalServiceDto getById(Long id) {
        return medicalServiseMapper.toDto(
                medicalServiseRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Medical service not found"))
        );
    }

    public MedicalServiceDto create(MedicalServiceDto dto) {
        MedicalService entity = medicalServiseMapper.toEntity(dto);
        return medicalServiseMapper.toDto(medicalServiseRepository.save(entity));
    }

    public MedicalServiceDto update(Long id, MedicalServiceDto dto) {
        MedicalService existing = medicalServiseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical service not found"));
        MedicalService updated = medicalServiseMapper.toEntity(dto);
        updated.setId(existing.getId());
        return medicalServiseMapper.toDto(medicalServiseRepository.save(updated));
    }

    public void delete(Long id) {
        medicalServiseRepository.deleteById(id);
    }



}
