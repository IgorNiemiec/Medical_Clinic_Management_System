package org.example.medical_clinic_management_system.service.communication;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.communication.NotificationDto;
import org.example.medical_clinic_management_system.mapper.communication.NotificationMapper;
import org.example.medical_clinic_management_system.model.communication.Notification;
import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.repository.communication.NotificationRepository;
import org.example.medical_clinic_management_system.repository.person.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class NotificationService
{

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationMapper mapper;

    public List<NotificationDto> getAll() {
        return notificationRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public NotificationDto getById(Long id) {
        return notificationRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    public NotificationDto create(NotificationDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Notification entity = mapper.toEntity(dto, user);
        return mapper.toDto(notificationRepository.save(entity));
    }

    public NotificationDto update(Long id, NotificationDto dto) {
        Notification existing = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Notification updated = mapper.toEntity(dto, user);
        updated.setId(id);
        return mapper.toDto(notificationRepository.save(updated));
    }

    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }


}
