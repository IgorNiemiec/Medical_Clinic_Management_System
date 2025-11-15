package org.example.medical_clinic_management_system.dto.schedule;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import org.example.medical_clinic_management_system.model.schedule.WaitingList.Priority;
import org.example.medical_clinic_management_system.model.schedule.WaitingList.Status;

import java.time.LocalDateTime;

@Data
public class WaitingListDto
{
    private Long id;

    @NotNull(message = "Patient ID cannot be null")
    private Long patientId;

    @NotNull(message = "Added date is required")
    private LocalDateTime addedAt;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @NotNull(message = "Status is required")
    private Status status;
}
