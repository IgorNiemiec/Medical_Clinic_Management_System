package org.example.medical_clinic_management_system.dto.communication;

import lombok.Data;
import org.example.medical_clinic_management_system.model.communication.Notification.Status;
import org.example.medical_clinic_management_system.model.communication.Notification.Type;

import java.time.LocalDate;

@Data
public class NotificationDto
{
    private Long id;
    private Integer userId;
    private Type type;
    private String content;
    private LocalDate date;
    private Status status;
}
