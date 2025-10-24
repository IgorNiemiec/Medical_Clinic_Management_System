package org.example.medical_clinic_management_system.service.payment;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.payment.PaymentDto;
import org.example.medical_clinic_management_system.mapper.payment.PaymentMapper;
import org.example.medical_clinic_management_system.model.payment.Payment;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.example.medical_clinic_management_system.repository.payment.PaymentRepository;
import org.example.medical_clinic_management_system.repository.visit.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService
{

    private final PaymentRepository repository;
    private final AppointmentRepository appointmentRepository;
    private final PaymentMapper mapper;

    public List<PaymentDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public PaymentDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public PaymentDto create(PaymentDto dto) {
        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        Payment entity = mapper.toEntity(dto, appointment);
        return mapper.toDto(repository.save(entity));
    }

    public PaymentDto update(Long id, PaymentDto dto) {
        Payment existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        Payment updated = mapper.toEntity(dto, appointment);
        updated.setId(id);
        return mapper.toDto(repository.save(updated));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
