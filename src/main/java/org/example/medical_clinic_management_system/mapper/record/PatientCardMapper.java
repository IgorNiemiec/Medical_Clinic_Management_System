package org.example.medical_clinic_management_system.mapper.record;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.DiseaseCourseDetailsDto;
import org.example.medical_clinic_management_system.dto.record.PatientCardDetailsDto;
import org.example.medical_clinic_management_system.dto.record.PatientCardDto;
import org.example.medical_clinic_management_system.dto.record.PatientCardRequestDto;
import org.example.medical_clinic_management_system.mapper.person.PatientMapper;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.record.PatientCard;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PatientCardMapper
{

    private final PatientMapper patientMapper;
    private final DiseaseCourseMapper diseaseCourseMapper;


    public PatientCardDetailsDto toDetailsDto(PatientCard entity) {
        if (entity == null) {
            return null;
        }

        PatientCardDetailsDto dto = new PatientCardDetailsDto();
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setLastUpdate(entity.getLastUpdate());

        if (entity.getPatient() != null) {

            dto.setPatient(patientMapper.toDetailsDto(entity.getPatient()));
        }

        if (entity.getDiseaseCourses() != null && !entity.getDiseaseCourses().isEmpty()) {
            List<DiseaseCourseDetailsDto> courses = entity.getDiseaseCourses().stream()
                    .map(diseaseCourseMapper::toDetailsDto)
                    .sorted(Comparator.comparing(DiseaseCourseDetailsDto::getDiagnosisDate).reversed())
                    .collect(Collectors.toList());

            dto.setDiseaseCourses(courses);
            dto.setTotalDiseaseCourseCount(courses.size());
        } else {
            dto.setTotalDiseaseCourseCount(0);
        }

        return dto;
    }

    public PatientCard toEntity(PatientCardRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return PatientCard.builder()
                .build();
    }







}
