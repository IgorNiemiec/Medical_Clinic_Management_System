package org.example.medical_clinic_management_system.dto.visit;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom.Status;

@Data
@Builder
public class ExaminationRoomDto {
    private Long id;

    @NotBlank(message = "{validation.examinationRoom.number.notBlank}")
    @Size(min = 1, max = 10, message = "{validation.examinationRoom.number.size}")
    private String number;

    @NotBlank(message = "{validation.examinationRoom.purpose.notBlank}")
    @Size(min = 3, max = 100, message = "{validation.examinationRoom.purpose.size}")
    private String purpose;

    @NotBlank(message = "{validation.examinationRoom.location.notBlank}")
    @Size(min = 3, max = 255, message = "{validation.examinationRoom.location.size}")
    private String location;

    @NotNull(message = "{validation.examinationRoom.status.notNull}")
    private Status status;
}
