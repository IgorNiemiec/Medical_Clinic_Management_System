package org.example.medical_clinic_management_system.dto.visit;

import lombok.Builder;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom.Status;

@Data
@Builder
public class ExaminationRoomDto
{
    private Long id;
    private String number;
    private String purpose;
    private String location;
    private Status status;
}
