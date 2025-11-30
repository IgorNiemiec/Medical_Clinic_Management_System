package org.example.medical_clinic_management_system.mapper.pharmacy;

import org.example.medical_clinic_management_system.dto.pharmacy.PrescriptionDetailsDto;
import org.example.medical_clinic_management_system.dto.pharmacy.PrescriptionRequestDto;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.pharmacy.Drug;
import org.example.medical_clinic_management_system.model.pharmacy.Prescription;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrescriptionMapper
{
    public PrescriptionDetailsDto toDetailsDto(Prescription entity) {
        if (entity == null) {
            return null;
        }

        PrescriptionDetailsDto dto = new PrescriptionDetailsDto();
        dto.setId(entity.getId());


        dto.setExpirationDate(entity.getExpirationDate());
        dto.setDosageInstructions(entity.getDosageInstructions());
        dto.setQuantity(entity.getQuantity());


        Patient patient = entity.getPatient();
        dto.setPatientId(patient.getId());
        dto.setPatientFullName(patient.getUser().getFirstName() + " " + patient.getUser().getSurname());


        MedicalStaff doctor = entity.getDoctor();
        dto.setDoctorId(doctor.getId());


         dto.setDoctorFullName(entity.getDoctor().getEmployee().getUser().getFirstName() + " " + entity.getDoctor().getEmployee().getUser().getSurname());


        Drug drug = entity.getDrug();
        dto.setDrugId(drug.getId());


        dto.setDrugProductName(drug.getProductName());
        dto.setDrugCommonName(drug.getCommonName());
        dto.setDrugForm(drug.getForm());
        dto.setDrugAtcCode(drug.getAtcCode());

        return dto;
    }

    public List<PrescriptionDetailsDto> toDetailsDtoList(List<Prescription> entities) {
        return entities.stream()
                .map(this::toDetailsDto)
                .collect(Collectors.toList());
    }

    public Prescription toEntity(PrescriptionRequestDto dto) {
        if (dto == null)
        {
            return null;
        }

        return Prescription.builder()
                .expirationDate(dto.getExpirationDate())
                .dosageInstructions(dto.getDosageInstructions())
                .quantity(dto.getQuantity())
                .build();
    }




}
