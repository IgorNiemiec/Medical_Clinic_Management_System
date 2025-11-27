package org.example.medical_clinic_management_system.mapper.person;

import org.example.medical_clinic_management_system.dto.person.UserDetailsDto;
import org.example.medical_clinic_management_system.dto.person.UserDto;
import org.example.medical_clinic_management_system.dto.person.UserRequestDto;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper
{

    public User toEntity(UserRequestDto requestDto) {
        return User.builder()
                .firstName(requestDto.getFirstName())
                .surname(requestDto.getSurname())
                .email(requestDto.getEmail())
                .role(requestDto.getRole())
                .build();
    }

    public UserDetailsDto toDto(User user) {
        UserDetailsDto dto = new UserDetailsDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setSurname(user.getSurname());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setRegisterDate(user.getRegisterDate());
        dto.setLastLogin(user.getLastLogin());
        return dto;
    }

    public List<UserDetailsDto> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


    public void updateEntityFromDto(User existingUser, UserRequestDto requestDto)
    {
        existingUser.setFirstName(requestDto.getFirstName());
        existingUser.setSurname(requestDto.getSurname());
        existingUser.setEmail(requestDto.getEmail());
        existingUser.setRole(requestDto.getRole());
    }





}
