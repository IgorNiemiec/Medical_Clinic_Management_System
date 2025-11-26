package org.example.medical_clinic_management_system.mapper.record;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.DiseaseCourseDetailsDto;
import org.example.medical_clinic_management_system.dto.record.DiseaseCourseRequestDto;
import org.example.medical_clinic_management_system.mapper.person.MedicalStaffMapper;
import org.example.medical_clinic_management_system.model.record.DiseaseCourse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DiseaseCourseMapper
{

    private final ICDCodeMapper icdCodeMapper;
    private final MedicalStaffMapper medicalStaffMapper;


    public DiseaseCourseDetailsDto toDetailsDto(DiseaseCourse entity) {
        if (entity == null) {
            return null;
        }

        DiseaseCourseDetailsDto dto = new DiseaseCourseDetailsDto();
        dto.setId(entity.getId());
        dto.setDiagnosisDate(entity.getDiagnosisDate());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());


        if (entity.getPatientCard() != null) {
            dto.setPatientCardId(entity.getPatientCard().getId());
        }

        if (entity.getIcdCode() != null) {
            dto.setIcdCode(icdCodeMapper.toDto(entity.getIcdCode()));
        }

        if (entity.getMedicalStaff() != null) {
            dto.setMedicalStaff(medicalStaffMapper.toDto(entity.getMedicalStaff()));
        }

        return dto;
    }


    public List<DiseaseCourseDetailsDto> toDetailsDtoList(List<DiseaseCourse> entities) {
        return entities.stream()
                .map(this::toDetailsDto)
                .collect(Collectors.toList());
    }

    public DiseaseCourse toEntity(DiseaseCourseRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return DiseaseCourse.builder()
                .diagnosisDate(dto.getDiagnosisDate())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
    }

    public void updateEntity(DiseaseCourse entity, DiseaseCourseRequestDto dto) {


        if (dto.getDiagnosisDate() != null) {
            entity.setDiagnosisDate(dto.getDiagnosisDate());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }

    }










}
