package org.example.medical_clinic_management_system.mapper.person;

import org.example.medical_clinic_management_system.dto.person.MedicalStaffDto;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.stereotype.Component;

@Component
public class MedicalStaffMapper
{

    public MedicalStaffDto toDto(MedicalStaff entity) {
        MedicalStaffDto dto = new MedicalStaffDto();
        dto.setId(entity.getId());
        dto.setUserId((long) entity.getUser().getId());
        dto.setEmploymentDate(entity.getEmploymentDate());
        dto.setWorkPhone(entity.getWorkPhone());
        dto.setAvailable(entity.isAvailable());
        dto.setProfession(entity.getProfession());
        return dto;
    }

    public MedicalStaff toEntity(MedicalStaffDto dto, User user) {
        return MedicalStaff.builder()
                .id(dto.getId())
                .user(user)
                .employmentDate(dto.getEmploymentDate())
                .workPhone(dto.getWorkPhone())
                .available(dto.isAvailable())
                .profession(dto.getProfession())
                .build();
    }



}
