package org.example.medical_clinic_management_system.mapper.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.EmployeeDetailsDto;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffDetailsDto;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffDto;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffRequestDto;
import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicalStaffMapper
{

    private final EmployeeMapper employeeMapper;

    public MedicalStaff toEntity(MedicalStaffRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return MedicalStaff.builder()
                .profession(dto.getProfession())
                .licenseNumber(dto.getLicenseNumber())
                .availability(dto.isAvailability())
                .build();
    }

    public MedicalStaffDetailsDto toDetailsDto(MedicalStaff entity) {
        if (entity == null) {
            return null;
        }

        EmployeeDetailsDto employeeDetails = employeeMapper.toDetailsDto(entity.getEmployee());

        MedicalStaffDetailsDto dto = new MedicalStaffDetailsDto();
        dto.setId(entity.getId());
        dto.setEmployeeId(entity.getEmployee().getId());
        dto.setEmployee(employeeDetails);
        dto.setProfession(entity.getProfession());
        dto.setLicenseNumber(entity.getLicenseNumber());
        dto.setAvailability(entity.isAvailability());
        return dto;
    }

    public MedicalStaff updateEntityFromDto(MedicalStaff entity, MedicalStaffRequestDto dto) {
        if (dto == null || entity == null) {
            return entity;
        }

        entity.setProfession(dto.getProfession());
        entity.setLicenseNumber(dto.getLicenseNumber());
        entity.setAvailability(dto.isAvailability());

        return entity;
    }





}
