package org.example.medical_clinic_management_system.dto.person;

import lombok.Data;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;

@Data
public class MedicalStaffDetailsDto
{
    private Long id;
    private Long employeeId;
    private EmployeeDetailsDto employee;
    private MedicalStaff.Profession profession;
    private String licenseNumber;
    private boolean availability;
}
