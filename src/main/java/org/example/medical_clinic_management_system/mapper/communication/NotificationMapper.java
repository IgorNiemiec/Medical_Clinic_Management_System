package org.example.medical_clinic_management_system.mapper.communication;

import org.example.medical_clinic_management_system.dto.communication.NotificationDto;
import org.example.medical_clinic_management_system.model.communication.Notification;
import org.example.medical_clinic_management_system.model.person.User;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper
{

    public NotificationDto toDto(Notification entity) {
        NotificationDto dto = new NotificationDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setType(entity.getType());
        dto.setContent(entity.getContent());
        dto.setDate(entity.getDate());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public Notification toEntity(NotificationDto dto, User user) {
        return Notification.builder()
                .id(dto.getId())
                .user(user)
                .type(dto.getType())
                .content(dto.getContent())
                .date(dto.getDate())
                .status(dto.getStatus())
                .build();
    }



}
