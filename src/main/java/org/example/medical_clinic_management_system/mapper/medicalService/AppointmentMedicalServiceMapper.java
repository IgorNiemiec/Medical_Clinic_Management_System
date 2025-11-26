package org.example.medical_clinic_management_system.mapper.medicalService;

import org.example.medical_clinic_management_system.dto.medicalService.AppointmentMedicalServiceDetailsDto;
import org.example.medical_clinic_management_system.model.medicalService.AppointmentMedicalService;
import org.example.medical_clinic_management_system.model.medicalService.MedicalService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentMedicalServiceMapper
{


    public AppointmentMedicalServiceDetailsDto toDetailsDto(AppointmentMedicalService entity) {
        if (entity == null) {
            return null;
        }

        AppointmentMedicalServiceDetailsDto dto = new AppointmentMedicalServiceDetailsDto();
        dto.setId(entity.getId());


        dto.setAppointmentId(entity.getAppointment().getId());

        dto.setInvoiceId(entity.getInvoice().getId());

        MedicalService medicalService = entity.getMedicalService();
        dto.setMedicalServiceId(medicalService.getId());

        dto.setServiceTitle(entity.getServiceTitleSnapshot() != null ? entity.getServiceTitleSnapshot() : medicalService.getTitle());


        dto.setPriceAtTime(entity.getPriceAtTime());
        dto.setQuantity(entity.getQuantity());


        dto.setTotalCost(entity.getTotalCost());

        dto.setBillingNote(entity.getBillingNote());

        return dto;
    }

    public List<AppointmentMedicalServiceDetailsDto> toDetailsDtoList(List<AppointmentMedicalService> entities) {
        return entities.stream()
                .map(this::toDetailsDto)
                .collect(Collectors.toList());
    }




}
