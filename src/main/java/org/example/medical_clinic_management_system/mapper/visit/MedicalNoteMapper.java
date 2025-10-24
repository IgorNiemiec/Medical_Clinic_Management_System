package org.example.medical_clinic_management_system.mapper.visit;


import org.example.medical_clinic_management_system.dto.visit.MedicalNoteDto;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.example.medical_clinic_management_system.model.visit.MedicalNote;
import org.springframework.stereotype.Component;

@Component
public class MedicalNoteMapper
{

    public MedicalNoteDto toDto(MedicalNote entity) {
        return MedicalNoteDto.builder()
                .id(entity.getId())
                .appointmentId(entity.getAppointment().getId())
                .content(entity.getContent())
                .date(entity.getDate())
                .type(entity.getType())
                .build();
    }

    public MedicalNote toEntity(MedicalNoteDto dto) {
        return MedicalNote.builder()
                .id(dto.getId())
                .appointment(Appointment.builder().id(dto.getAppointmentId()).build())
                .content(dto.getContent())
                .date(dto.getDate())
                .type(dto.getType())
                .build();
    }


}
