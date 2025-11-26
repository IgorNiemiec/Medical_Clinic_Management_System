package org.example.medical_clinic_management_system.dto.visit;

import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;

@Data
public class ExaminationRoomRequestDto
{

    private String number;

    private String purpose;

    private ExaminationRoom.ExaminationRoomStatus status;

}
