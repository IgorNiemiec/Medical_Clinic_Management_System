package org.example.medical_clinic_management_system.mapper.payment;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medicalService.AppointmentMedicalServiceDetailsDto;
import org.example.medical_clinic_management_system.dto.payment.InvoiceDetailsDto;
import org.example.medical_clinic_management_system.mapper.medicalService.AppointmentMedicalServiceMapper;
import org.example.medical_clinic_management_system.model.payment.Invoice;
import org.example.medical_clinic_management_system.service.payment.PaymentService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InvoiceMapper
{

    private final AppointmentMedicalServiceMapper amsMapper;
    private final PaymentService paymentService;

    public InvoiceDetailsDto toDetailsDto(Invoice entity) {
        if (entity == null) {
            return null;
        }

        InvoiceDetailsDto dto = new InvoiceDetailsDto();
        dto.setId(entity.getId());
        dto.setInvoiceNumber(entity.getInvoiceNumber());
        dto.setPatientId(entity.getPatient().getId());

        dto.setIssueDate(entity.getIssueDate());
        dto.setDueDate(entity.getDueDate());
        dto.setStatus(entity.getStatus());

        dto.setTotalNet(entity.getTotalNet());
        dto.setTotalGross(entity.getTotalGross());
        dto.setPayerName(entity.getPayerName());
        dto.setPayerAddress(entity.getPayerAddress());
        dto.setPayerNip(entity.getPayerNip());

        List<AppointmentMedicalServiceDetailsDto> itemDtos = entity.getItems().stream()
                .map(amsMapper::toDetailsDto)
                .collect(Collectors.toList());
        dto.setItems(itemDtos);


        if (!itemDtos.isEmpty()) {
            Long appointmentId = itemDtos.get(0).getAppointmentId();
            BigDecimal totalPaid = paymentService.calculateTotalPaid(appointmentId);
            BigDecimal outstandingBalance = entity.getTotalGross().subtract(totalPaid);

            dto.setTotalPaid(totalPaid);
            dto.setOutstandingBalance(outstandingBalance);
        } else {
            dto.setTotalPaid(BigDecimal.ZERO);
            dto.setOutstandingBalance(entity.getTotalGross());
        }


        return dto;
    }

    public List<InvoiceDetailsDto> toDetailsDtoList(List<Invoice> entities) {
        return entities.stream()
                .map(this::toDetailsDto)
                .collect(Collectors.toList());
    }


}
