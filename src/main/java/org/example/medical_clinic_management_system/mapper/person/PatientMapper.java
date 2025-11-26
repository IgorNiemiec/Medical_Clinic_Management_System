package org.example.medical_clinic_management_system.mapper.person;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.PatientDetailsDto;
import org.example.medical_clinic_management_system.dto.person.PatientDto;
import org.example.medical_clinic_management_system.dto.person.PatientListItemDto;
import org.example.medical_clinic_management_system.dto.person.PatientRequestDto;
import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PatientMapper
{

    public PatientDetailsDto toDetailsDto(Patient entity) {
        if (entity == null) {
            return null;
        }

        PatientDetailsDto dto = new PatientDetailsDto();
        dto.setId(entity.getId());

        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setPesel(entity.getPesel());
        dto.setGender(entity.getGender());

        User user = entity.getUser();
        if (user != null) {
            dto.setUserId(user.getId());
            dto.setFirstName(user.getFirstName());
            dto.setSurname(user.getSurname());
            dto.setEmail(user.getEmail());
        }

        Employee registeredBy = entity.getRegisteredBy();
        if (registeredBy != null) {
            dto.setRegisteredByEmployeeId(registeredBy.getId());


            User registeredByUser = registeredBy.getUser();
            if (registeredByUser != null) {
                dto.setRegisteredByEmployeeFullName(registeredByUser.getFirstName() + " " + registeredByUser.getSurname());
            }
        }

        return dto;
    }

    public PatientListItemDto toListItemDto(Patient entity) {
        if (entity == null) {
            return null;
        }

        PatientListItemDto dto = new PatientListItemDto();
        dto.setId(entity.getId());
        dto.setPesel(entity.getPesel());
        dto.setPhoneNumber(entity.getPhoneNumber());

        User user = entity.getUser();
        if (user != null) {
            dto.setFirstName(user.getFirstName());
            dto.setSurname(user.getSurname());
        }

        return dto;
    }

    public List<PatientListItemDto> toListItemDtoList(List<Patient> entities) {
        return entities.stream()
                .map(this::toListItemDto)
                .collect(Collectors.toList());
    }

    public Patient toEntity(PatientRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Patient.builder()
                .dateOfBirth(dto.getDateOfBirth())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .pesel(dto.getPesel())
                .gender(dto.getGender())
                .build();
    }





}
