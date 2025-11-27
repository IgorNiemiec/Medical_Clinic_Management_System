package org.example.medical_clinic_management_system.service.person;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.UserDetailsDto;
import org.example.medical_clinic_management_system.dto.person.UserDto;
import org.example.medical_clinic_management_system.dto.person.UserRequestDto;
import org.example.medical_clinic_management_system.mapper.person.UserMapper;
import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.repository.person.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService
{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public List<UserDetailsDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Transactional
    public UserDetailsDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return userMapper.toDto(user);
    }


    @Transactional
    public UserDetailsDto createUser(UserRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("Email already exists: " + requestDto.getEmail());
        }

        User newUser = userMapper.toEntity(requestDto);

        newUser.setRegisterDate(LocalDateTime.now());
        newUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        User savedUser = userRepository.save(newUser);
        return userMapper.toDto(savedUser);
    }

    @Transactional
    public UserDetailsDto updateUser(Long id, UserRequestDto requestDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));


        if (!existingUser.getEmail().equals(requestDto.getEmail()) && userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("Email already exists: " + requestDto.getEmail());
        }

        userMapper.updateEntityFromDto(existingUser, requestDto);

        // TODO: umożliwimy może zmienę hasła przez PUT, dodamy dedykowaną logikę do hashowania
        if (requestDto.getPassword() != null && !requestDto.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        }

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDto(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }





}
