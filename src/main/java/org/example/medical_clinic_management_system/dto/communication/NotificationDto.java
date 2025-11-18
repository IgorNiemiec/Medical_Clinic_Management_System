package org.example.medical_clinic_management_system.dto.communication;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.example.medical_clinic_management_system.model.communication.Notification.Status;
import org.example.medical_clinic_management_system.model.communication.Notification.Type;

import java.time.LocalDate;

@Data
public class NotificationDto {
    private Long id;

    @NotNull(message = "{validation.notification.userId.notNull}")
    private Long userId;

    @NotNull(message = "{validation.notification.type.notNull}")
    private Type type;

    @NotBlank(message = "{validation.notification.content.notBlank}")
    @Size(min = 5, max = 500, message = "{validation.notification.content.size}")
    private String content;

    private LocalDate date;

    @NotNull(message = "{validation.notification.status.notNull}")
    private Status status;
}
