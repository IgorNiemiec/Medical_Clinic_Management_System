package org.example.medical_clinic_management_system.dto.visit;

import lombok.Data;
import org.example.medical_clinic_management_system.model.visit.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AppointmentDetailsDto
{

    private Long id;

    private LocalDate date;
    private LocalDateTime time;

    private Appointment.AppointmentType type;
    private Appointment.AppointmentStatus status;
    private String description;


    private Long patientId;
    private String patientFullName;

    private Long medicalStaffId;
    private String medicalStaffFullName;
    private String medicalStaffProfession;

    private Long examinationRoomId;
    private String examinationRoomNumber;


}
