package org.example.medical_clinic_management_system.service.schedule;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.schedule.ScheduleDto;
import org.example.medical_clinic_management_system.mapper.schedule.ScheduleMapper;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Receptionist;
import org.example.medical_clinic_management_system.model.schedule.Schedule;
import org.example.medical_clinic_management_system.repository.person.MedicalStaffRepository;
import org.example.medical_clinic_management_system.repository.person.ReceptionistRepository;
import org.example.medical_clinic_management_system.repository.schedule.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService
{

    private final ScheduleRepository repository;
    private final ScheduleMapper mapper;
    private final MedicalStaffRepository staffRepository;
    private final ReceptionistRepository receptionistRepository;


    public List<ScheduleDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public ScheduleDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public ScheduleDto create(ScheduleDto dto) {
        MedicalStaff medicalStaff = staffRepository.findById(dto.getMedicalStaffId())
                .orElseThrow(() -> new RuntimeException("Medical staff not found"));
        Receptionist receptionist = receptionistRepository.findById(dto.getReceptionistId())
                .orElseThrow(() -> new RuntimeException("Receptionist not found"));

        Schedule entity = mapper.toEntity(dto,medicalStaff,receptionist);
        return mapper.toDto(repository.save(entity));
    }

    public ScheduleDto update(Long id,ScheduleDto dto) {
        Schedule existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        MedicalStaff medicalStaff = staffRepository.findById(dto.getMedicalStaffId())
                .orElseThrow(() -> new RuntimeException("Medical staff not found"));
        Receptionist receptionist = receptionistRepository.findById(dto.getReceptionistId())
                .orElseThrow(() -> new RuntimeException("Receptionist not found"));

        Schedule updated = mapper.toEntity(dto, medicalStaff,receptionist);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


}
