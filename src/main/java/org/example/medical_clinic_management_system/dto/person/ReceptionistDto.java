package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ReceptionistDto {
    private Long id;

    @NotNull(message = "{validation.receptionist.employeeId.notNull}")
    private Long employeeId;

}
