package org.example.medical_clinic_management_system.dto.person;


import lombok.Data;

import java.time.LocalDate;

@Data
public class ReceptionistDto
{
    private Long id;
    private Long userId;
    private LocalDate employmentDate;
    private String workPhone;
}
