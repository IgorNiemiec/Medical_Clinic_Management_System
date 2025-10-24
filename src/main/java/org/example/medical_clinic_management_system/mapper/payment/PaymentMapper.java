package org.example.medical_clinic_management_system.mapper.payment;

import org.example.medical_clinic_management_system.dto.payment.PaymentDto;
import org.example.medical_clinic_management_system.model.payment.Payment;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper
{

    public PaymentDto toDto(Payment entity) {
        PaymentDto dto = new PaymentDto();
        dto.setId(entity.getId());
        dto.setAppointmentId(entity.getAppointment().getId());
        dto.setAmount(entity.getAmount());
        dto.setDate(entity.getDate());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setStatus(entity.getStatus());
        dto.setTransactionNumber(entity.getTransactionNumber());
        return dto;
    }

    public Payment toEntity(PaymentDto dto, Appointment appointment) {
        return Payment.builder()
                .id(dto.getId())
                .appointment(appointment)
                .amount(dto.getAmount())
                .date(dto.getDate())
                .paymentMethod(dto.getPaymentMethod())
                .status(dto.getStatus())
                .transactionNumber(dto.getTransactionNumber())
                .build();
    }


}
