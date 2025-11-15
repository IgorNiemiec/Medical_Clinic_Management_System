package org.example.medical_clinic_management_system.dto.communication;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.example.medical_clinic_management_system.model.communication.Notification.Status;
import org.example.medical_clinic_management_system.model.communication.Notification.Type;


import java.time.LocalDate;



@Data
public class NotificationDto
{
    private Long id;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Type is required")
    private Type type;

    @NotBlank(message = "Content cannot be blank")
    @Size(min = 5, max = 500, message = "Content must be between 5 and 500 characters")
    private String content;

    private LocalDate date;

    @NotNull(message = "Status is required")
    private Status status;
}
