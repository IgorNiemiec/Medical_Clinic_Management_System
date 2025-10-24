package org.example.medical_clinic_management_system.mapper.record;

import org.example.medical_clinic_management_system.dto.record.DiseaseProgressionDto;
import org.example.medical_clinic_management_system.model.medical.Disease;
import org.example.medical_clinic_management_system.model.record.DiseaseProgression;
import org.example.medical_clinic_management_system.model.record.PatientCard;
import org.springframework.stereotype.Component;

@Component
public class DiseaseProgressionMapper
{

    public DiseaseProgressionDto toDto(DiseaseProgression entity) {
        DiseaseProgressionDto dto = new DiseaseProgressionDto();
        dto.setId(entity.getId());
        dto.setPatientCardId(entity.getPatientCard().getId());
        dto.setDiseaseId(entity.getDisease().getId());
        dto.setDiagnosisDate(entity.getDiagnosisDate());
        dto.setSymptoms(entity.getSymptoms());
        dto.setTreatment(entity.getTreatment());
        return dto;
    }

    public DiseaseProgression toEntity(DiseaseProgressionDto dto, PatientCard card, Disease disease) {
        return DiseaseProgression.builder()
                .id(dto.getId())
                .patientCard(card)
                .disease(disease)
                .diagnosisDate(dto.getDiagnosisDate())
                .symptoms(dto.getSymptoms())
                .treatment(dto.getTreatment())
                .build();
    }

}
