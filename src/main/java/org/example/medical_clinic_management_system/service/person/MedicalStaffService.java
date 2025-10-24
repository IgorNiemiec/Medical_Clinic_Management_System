package org.example.medical_clinic_management_system.service.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffDto;
import org.example.medical_clinic_management_system.mapper.person.MedicalStaffMapper;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.repository.person.MedicalStaffRepository;
import org.example.medical_clinic_management_system.repository.person.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalStaffService
{

    private final MedicalStaffRepository repository;
    private final UserRepository userRepository;
    private final MedicalStaffMapper mapper;

    public List<MedicalStaffDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public MedicalStaffDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Medical staff not found"));
    }

    public MedicalStaffDto create(MedicalStaffDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        MedicalStaff entity = mapper.toEntity(dto, user);
        return mapper.toDto(repository.save(entity));
    }

    public MedicalStaffDto update(Long id, MedicalStaffDto dto) {
        MedicalStaff existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical staff not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        MedicalStaff updated = mapper.toEntity(dto, user);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
