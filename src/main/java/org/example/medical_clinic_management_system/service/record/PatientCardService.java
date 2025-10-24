package org.example.medical_clinic_management_system.service.record;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.PatientCardDto;
import org.example.medical_clinic_management_system.mapper.record.PatientCardMapper;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.record.PatientCard;
import org.example.medical_clinic_management_system.repository.person.PatientRepository;
import org.example.medical_clinic_management_system.repository.record.PatientCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientCardService
{
    private final PatientCardRepository repository;
    private final PatientRepository patientRepository;
    private final PatientCardMapper mapper;

    public List<PatientCardDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientCardDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Patient card not found"));
    }

    public PatientCardDto create(PatientCardDto dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        PatientCard entity = mapper.toEntity(dto, patient);
        return mapper.toDto(repository.save(entity));
    }

    public PatientCardDto update(Long id, PatientCardDto dto) {
        PatientCard existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient card not found"));
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        PatientCard updated = mapper.toEntity(dto, patient);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
