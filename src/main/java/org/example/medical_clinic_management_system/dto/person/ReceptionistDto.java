package org.example.medical_clinic_management_system.dto.person;


import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Data
public class ReceptionistDto
{
    private Long id;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Employment date is required")
    @PastOrPresent(message = "Employment date cannot be in the future")
    private LocalDate employmentDate;

    @NotBlank(message = "Work phone cannot be blank")
    @Pattern(regexp = "^[0-9\\-+]{9,15}$", message = "Work phone must be a valid number (9–15 digits)")
    private String workPhone;
}
