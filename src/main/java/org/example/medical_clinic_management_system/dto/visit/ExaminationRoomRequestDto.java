package org.example.medical_clinic_management_system.dto.visit;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;

@Data
public class ExaminationRoomRequestDto
{

    @NotBlank(message = "{validation.examinationRoom.number.notBlank}")
    @Size(max = 20, message = "{validation.examinationRoom.number.size}")
    private String number;

    @NotBlank(message = "{validation.examinationRoom.purpose.notBlank}")
    @Size(max = 255, message = "{validation.examinationRoom.purpose.size}")
    private String purpose;

    @NotNull(message = "{validation.examinationRoom.status.notNull}")
    private ExaminationRoom.ExaminationRoomStatus status;

}
