package org.example.medical_clinic_management_system.service.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.PatientDto;
import org.example.medical_clinic_management_system.mapper.person.PatientMapper;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.repository.person.PatientRepository;
import org.example.medical_clinic_management_system.repository.person.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService
{

    private final PatientRepository repository;
    private final UserRepository userRepository;
    private final PatientMapper mapper;

    public List<PatientDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    public PatientDto create(PatientDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Patient entity = mapper.toEntity(dto, user);
        return mapper.toDto(repository.save(entity));
    }

    public PatientDto update(Long id, PatientDto dto) {
        Patient existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Patient updated = mapper.toEntity(dto, user);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
