package org.example.medical_clinic_management_system.mapper.person;

import org.example.medical_clinic_management_system.dto.person.ReceptionistDto;
import org.example.medical_clinic_management_system.model.person.Receptionist;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.stereotype.Component;

@Component
public class ReceptionistMapper
{

    public ReceptionistDto toDto(Receptionist entity) {
        ReceptionistDto dto = new ReceptionistDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setEmploymentDate(entity.getEmploymentDate());
        dto.setWorkPhone(entity.getWorkPhone());
        return dto;
    }

    public Receptionist toEntity(ReceptionistDto dto, User user) {
        return Receptionist.builder()
                .id(dto.getId())
                .user(user)
                .employmentDate(dto.getEmploymentDate())
                .workPhone(dto.getWorkPhone())
                .build();
    }

}
