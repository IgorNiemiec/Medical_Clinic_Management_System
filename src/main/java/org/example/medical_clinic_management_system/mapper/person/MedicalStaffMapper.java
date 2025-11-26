package org.example.medical_clinic_management_system.mapper.person;

import org.example.medical_clinic_management_system.dto.person.MedicalStaffDto;
import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.stereotype.Component;

@Component
public class MedicalStaffMapper
{

    public MedicalStaffDto toDto(MedicalStaff entity) {
        MedicalStaffDto dto = new MedicalStaffDto();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setAvailability(entity.isAvailability());
        dto.setProfession(entity.getProfession());
        return dto;
    }

    public MedicalStaff toEntity(MedicalStaffDto dto, Employee employee) {
        return MedicalStaff.builder()
                .id(dto.getId())
                .employee(employee)
                .availability(dto.isAvailability())
                .profession(dto.getProfession())
                .build();
    }



}
