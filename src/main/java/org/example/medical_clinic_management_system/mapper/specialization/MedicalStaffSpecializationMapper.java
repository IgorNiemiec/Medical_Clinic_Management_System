package org.example.medical_clinic_management_system.mapper.specialization;

import org.example.medical_clinic_management_system.dto.person.MedicalStaffDto;
import org.example.medical_clinic_management_system.dto.specialization.StaffSpecializationDetailsDTO;
import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.specialization.MedicalStaffSpecialization;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicalStaffSpecializationMapper
{

    public StaffSpecializationDetailsDTO toDetailsDto(MedicalStaffSpecialization entity) {
        if (entity == null) {
            return null;
        }

        StaffSpecializationDetailsDTO dto = new StaffSpecializationDetailsDTO();

        dto.setStaffId(entity.getMedicalStaff().getId());
        dto.setSpecializationId(entity.getSpecialization().getId());
        dto.setDateCertified(entity.getDateCertified());

        dto.setSpecializationTitle(entity.getSpecialization().getName());

        return dto;

    }

    public List<StaffSpecializationDetailsDTO> toDetailsDtoList(List<MedicalStaffSpecialization> entities) {
        return entities.stream()
                .map(this::toDetailsDto)
                .collect(Collectors.toList());
    }





}
