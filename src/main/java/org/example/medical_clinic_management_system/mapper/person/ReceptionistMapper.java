package org.example.medical_clinic_management_system.mapper.person;

import org.example.medical_clinic_management_system.dto.person.ReceptionistDto;
import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.Receptionist;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.stereotype.Component;

@Component
public class ReceptionistMapper
{

    public ReceptionistDto toDto(Receptionist entity) {
        ReceptionistDto dto = new ReceptionistDto();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getId());
        return dto;
    }

    public Receptionist toEntity(ReceptionistDto dto, Employee employee) {
        return Receptionist.builder()
                .id(dto.getId())
                .employee(employee)
                .build();
    }

}
