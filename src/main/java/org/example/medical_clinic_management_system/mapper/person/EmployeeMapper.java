package org.example.medical_clinic_management_system.mapper.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.*;
import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmployeeMapper
{

    public EmployeeDetailsDto toDetailsDto(Employee entity) {
        if (entity == null) {
            return null;
        }

        EmployeeDetailsDto dto = new EmployeeDetailsDto();
        dto.setId(entity.getId());

        dto.setHireDate(entity.getHireDate());
        dto.setServicePhone(entity.getServicePhone());

        User user = entity.getUser();
        dto.setUserId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        dto.setRegisterDate(user.getRegisterDate());

        return dto;
    }

    public EmployeeListItemDto toListItemDto(Employee entity) {
        if (entity == null) {
            return null;
        }

        EmployeeListItemDto dto = new EmployeeListItemDto();
        dto.setId(entity.getId());
        dto.setServicePhone(entity.getServicePhone());

        User user = entity.getUser();
        dto.setUserId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setSurname(user.getSurname());

        return dto;
    }


    public Employee toEntity(EmployeeRequestDto dto) {
        if (dto == null)
        {
            return null;
        }

        return Employee.builder()
                .hireDate(dto.getHireDate())
                .servicePhone(dto.getServicePhone())
                .build();
    }

    public List<EmployeeListItemDto> toListItemDtoList(List<Employee> entities) {
        return entities.stream()
                .map(this::toListItemDto)
                .collect(Collectors.toList());
    }



}
