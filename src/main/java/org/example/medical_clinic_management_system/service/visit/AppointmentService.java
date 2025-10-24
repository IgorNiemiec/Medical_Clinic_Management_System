package org.example.medical_clinic_management_system.service.visit;


import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.AppointmentDto;
import org.example.medical_clinic_management_system.mapper.visit.AppointmentMapper;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.example.medical_clinic_management_system.repository.visit.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService
{

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    public List<AppointmentDto> getAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public AppointmentDto getById(Long id) {
        return appointmentMapper.toDto(
                appointmentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Appointment not found"))
        );
    }

    public AppointmentDto create(AppointmentDto dto) {
        Appointment appointment = appointmentMapper.toEntity(dto);
        return appointmentMapper.toDto(appointmentRepository.save(appointment));
    }

    public AppointmentDto update(Long id, AppointmentDto dto) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        Appointment updated = appointmentMapper.toEntity(dto);
        updated.setId(existing.getId());
        return appointmentMapper.toDto(appointmentRepository.save(updated));
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }


}
