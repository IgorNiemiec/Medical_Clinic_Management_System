package org.example.medical_clinic_management_system.service.schedule;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.schedule.EmployeeScheduleDto;
import org.example.medical_clinic_management_system.mapper.schedule.EmployeeScheduleMapper;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.schedule.EmployeeSchedule;
import org.example.medical_clinic_management_system.repository.person.MedicalStaffRepository;
import org.example.medical_clinic_management_system.repository.schedule.EmployeeScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeScheduleService
{

    private final EmployeeScheduleRepository repository;
    private final MedicalStaffRepository staffRepository;
    private final EmployeeScheduleMapper mapper;

    public List<EmployeeScheduleDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public EmployeeScheduleDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public EmployeeScheduleDto create(EmployeeScheduleDto dto) {
        MedicalStaff employee = staffRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Medical staff not found"));
        EmployeeSchedule entity = mapper.toEntity(dto, employee);
        return mapper.toDto(repository.save(entity));
    }

    public EmployeeScheduleDto update(Long id, EmployeeScheduleDto dto) {
        EmployeeSchedule existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        MedicalStaff employee = staffRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Medical staff not found"));
        EmployeeSchedule updated = mapper.toEntity(dto, employee);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
