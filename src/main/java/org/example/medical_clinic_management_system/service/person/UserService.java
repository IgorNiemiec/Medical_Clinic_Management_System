package org.example.medical_clinic_management_system.service.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.UserDto;
import org.example.medical_clinic_management_system.mapper.person.UserMapper;
import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.repository.person.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService
{

    private final UserRepository repository;
    private final UserMapper mapper;

    public List<UserDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDto create(UserDto dto) {
        User entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public UserDto update(Long id, UserDto dto) {
        User existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User updated = mapper.toEntity(dto);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
