package org.example.medical_clinic_management_system.dto.schedule;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import org.example.medical_clinic_management_system.model.schedule.WaitingList.Priority;
import org.example.medical_clinic_management_system.model.schedule.WaitingList.Status;

import java.time.LocalDateTime;

@Data
public class WaitingListDto {
    private Long id;

    @NotNull(message = "{validation.waitingList.patientId.notNull}")
    private Long patientId;

    @NotNull(message = "{validation.waitingList.addedAt.notNull}")
    private LocalDateTime addedAt;

    @NotNull(message = "{validation.waitingList.priority.notNull}")
    private Priority priority;

    @NotNull(message = "{validation.waitingList.status.notNull}")
    private Status status;
}
