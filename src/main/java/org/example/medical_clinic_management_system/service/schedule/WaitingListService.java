package org.example.medical_clinic_management_system.service.schedule;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.schedule.WaitingListDto;
import org.example.medical_clinic_management_system.mapper.schedule.WaitingListMapper;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.schedule.WaitingList;
import org.example.medical_clinic_management_system.repository.person.PatientRepository;
import org.example.medical_clinic_management_system.repository.schedule.WaitingListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WaitingListService
{

    private final WaitingListRepository repository;
    private final PatientRepository patientRepository;
    private final WaitingListMapper mapper;

    public List<WaitingListDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public WaitingListDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Waiting list entry not found"));
    }

    public WaitingListDto create(WaitingListDto dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        WaitingList entity = mapper.toEntity(dto, patient);
        return mapper.toDto(repository.save(entity));
    }

    public WaitingListDto update(Long id, WaitingListDto dto) {
        WaitingList existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Waiting list entry not found"));
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        WaitingList updated = mapper.toEntity(dto, patient);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
