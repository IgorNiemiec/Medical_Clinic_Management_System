package org.example.medical_clinic_management_system.dto.person;
import lombok.Data;
import org.example.medical_clinic_management_system.model.person.MedicalStaff.Profession;

import java.time.LocalDate;

@Data
public class MedicalStaffDto
{
    private Long id;
    private Long userId;
    private LocalDate employmentDate;
    private String workPhone;
    private boolean available;
    private Profession profession;
}
