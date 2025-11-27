package org.example.medical_clinic_management_system.dto.person;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequestDto
{

    @NotNull(message = "{validation.employee.userId.notNull}")
    private Long userId;


    @NotNull(message = "{validation.employee.hireDate.notNull}")
    @FutureOrPresent(message = "{validation.employee.hireDate.futureOrPresent}")
    private LocalDate hireDate;

    @NotBlank(message = "{validation.employee.servicePhone.notBlank}")
    @Pattern(regexp = "^\\+?\\d{9,15}$", message = "{validation.employee.servicePhone.pattern}")
    private String servicePhone;

}
