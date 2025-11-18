package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ReceptionistDto {
    private Long id;

    @NotNull(message = "{validation.receptionist.userId.notNull}")
    private Long userId;

    @NotNull(message = "{validation.receptionist.employmentDate.notNull}")
    @PastOrPresent(message = "{validation.receptionist.employmentDate.pastOrPresent}")
    private LocalDate employmentDate;

    @NotBlank(message = "{validation.receptionist.workPhone.notBlank}")
    @Pattern(regexp = "^[0-9\\-+]{9,15}$", message = "{validation.receptionist.workPhone.pattern}")
    private String workPhone;
}
