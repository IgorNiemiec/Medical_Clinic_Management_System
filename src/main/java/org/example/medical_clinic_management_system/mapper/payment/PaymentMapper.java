package org.example.medical_clinic_management_system.mapper.payment;

import org.example.medical_clinic_management_system.dto.payment.PaymentDetailsDto;
import org.example.medical_clinic_management_system.dto.payment.PaymentDto;
import org.example.medical_clinic_management_system.model.payment.Payment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper
{


    public PaymentDetailsDto toDetailsDto(Payment entity) {
        if (entity == null) {
            return null;
        }

        PaymentDetailsDto dto = new PaymentDetailsDto();
        dto.setId(entity.getId());
        dto.setInvoiceId(entity.getInvoice().getId());
        dto.setAmount(entity.getAmount());
        dto.setPaymentMethod(entity.getPaymentMethod());
        dto.setPaymentDateTime(entity.getPaymentDateTime());
        dto.setTransactionReference(entity.getTransactionReference());

        return dto;
    }


    public List<PaymentDetailsDto> toDetailsDtoList(List<Payment> entities) {
        return entities.stream()
                .map(this::toDetailsDto)
                .collect(Collectors.toList());
    }

}
