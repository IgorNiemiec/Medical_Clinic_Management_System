package org.example.medical_clinic_management_system.mapper.person;

import org.example.medical_clinic_management_system.dto.person.UserDto;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper
{

    public UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword()); // Możesz pominąć w widoku publicznym
        dto.setRole(entity.getRole());
        dto.setRegisterDate(entity.getRegisterDate());
        dto.setLastLogin(entity.getLastLogin());
        return dto;
    }

    public User toEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(dto.getRole())
                .registerDate(dto.getRegisterDate())
                .lastLogin(dto.getLastLogin())
                .build();
    }

}
