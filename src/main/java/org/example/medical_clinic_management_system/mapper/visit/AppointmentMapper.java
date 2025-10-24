package org.example.medical_clinic_management_system.mapper.visit;

import org.example.medical_clinic_management_system.dto.visit.AppointmentDto;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.person.Receptionist;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper
{

    public AppointmentDto toDto(Appointment entity) {
        return AppointmentDto.builder()
                .id(entity.getId())
                .receptionistId(entity.getReceptionist().getId())
                .patientId(entity.getPatient().getId())
                .medicalStaffId(entity.getMedicalStaff().getId())
                .roomId(entity.getRoom().getId())
                .date(entity.getDate())
                .time(entity.getTime())
                .type(entity.getType())
                .status(entity.getStatus())
                .description(entity.getDescription())
                .build();
    }

    public Appointment toEntity(AppointmentDto dto) {
        return Appointment.builder()
                .id(dto.getId())
                .receptionist(Receptionist.builder().id(dto.getReceptionistId()).build())
                .patient(Patient.builder().id(dto.getPatientId()).build())
                .medicalStaff(MedicalStaff.builder().id(dto.getMedicalStaffId()).build())
                .room(ExaminationRoom.builder().id(dto.getRoomId()).build())
                .date(dto.getDate())
                .time(dto.getTime())
                .type(dto.getType())
                .status(dto.getStatus())
                .description(dto.getDescription())
                .build();
    }

}
