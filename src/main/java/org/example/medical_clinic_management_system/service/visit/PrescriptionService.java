package org.example.medical_clinic_management_system.service.visit;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.PrescriptionDto;
import org.example.medical_clinic_management_system.mapper.visit.PrescriptionMapper;
import org.example.medical_clinic_management_system.model.visit.Prescription;
import org.example.medical_clinic_management_system.repository.visit.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionService
{

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;

    public List<PrescriptionDto> getAll() {
        return prescriptionRepository.findAll()
                .stream()
                .map(prescriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    public PrescriptionDto getById(Long id) {
        return prescriptionMapper.toDto(
                prescriptionRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Prescription not found"))
        );
    }

    public PrescriptionDto create(PrescriptionDto dto) {
        Prescription prescription = prescriptionMapper.toEntity(dto);
        return prescriptionMapper.toDto(prescriptionRepository.save(prescription));
    }

    public PrescriptionDto update(Long id, PrescriptionDto dto) {
        Prescription existing = prescriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prescription not found"));
        Prescription updated = prescriptionMapper.toEntity(dto);
        updated.setId(existing.getId());
        return prescriptionMapper.toDto(prescriptionRepository.save(updated));
    }

    public void delete(Long id) {
        prescriptionRepository.deleteById(id);
    }


}
