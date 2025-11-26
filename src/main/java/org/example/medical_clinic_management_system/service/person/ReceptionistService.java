package org.example.medical_clinic_management_system.service.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.ReceptionistDto;
import org.example.medical_clinic_management_system.mapper.person.ReceptionistMapper;
import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.Receptionist;
import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.repository.person.EmployeeRepository;
import org.example.medical_clinic_management_system.repository.person.ReceptionistRepository;
import org.example.medical_clinic_management_system.repository.person.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceptionistService
{

    private final ReceptionistRepository repository;
    private final EmployeeRepository employeeRepository;
    private final ReceptionistMapper mapper;

    public List<ReceptionistDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public ReceptionistDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Receptionist not found"));
    }

    public ReceptionistDto create(ReceptionistDto dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Receptionist entity = mapper.toEntity(dto, employee);
        return mapper.toDto(repository.save(entity));
    }

    public ReceptionistDto update(Long id, ReceptionistDto dto) {
        Receptionist existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receptionist not found"));
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Receptionist updated = mapper.toEntity(dto, employee);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
