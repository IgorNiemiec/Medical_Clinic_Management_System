package org.example.medical_clinic_management_system.dto.schedule;

import lombok.Data;
import org.example.medical_clinic_management_system.model.schedule.WaitingList.Priority;
import org.example.medical_clinic_management_system.model.schedule.WaitingList.Status;

import java.time.LocalDateTime;

@Data
public class WaitingListDto
{
    private Long id;
    private Long patientId;
    private LocalDateTime addedAt;
    private Priority priority;
    private Status status;
}
