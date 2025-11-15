package org.example.medical_clinic_management_system.dto.visit;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom.Status;

@Data
@Builder
public class ExaminationRoomDto
{
    private Long id;

    @NotBlank(message = "Room number cannot be blank")
    @Size(min = 1, max = 10, message = "Room number must be between 1 and 10 characters")
    private String number;

    @NotBlank(message = "Purpose cannot be blank")
    @Size(min = 3, max = 100, message = "Purpose must be between 3 and 100 characters")
    private String purpose;

    @NotBlank(message = "Location cannot be blank")
    @Size(min = 3, max = 255, message = "Location must be between 3 and 255 characters")
    private String location;

    @NotNull(message = "Status is required")
    private Status status;
}
