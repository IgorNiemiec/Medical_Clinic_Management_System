package org.example.medical_clinic_management_system.mapper.visit;


import org.example.medical_clinic_management_system.dto.visit.MedicalNoteDetailsDto;
import org.example.medical_clinic_management_system.dto.visit.MedicalNoteDto;
import org.example.medical_clinic_management_system.dto.visit.MedicalNoteRequestDto;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.example.medical_clinic_management_system.model.visit.MedicalNote;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicalNoteMapper
{

    public MedicalNoteDetailsDto toDetailsDto(MedicalNote entity) {
        if (entity == null) {
            return null;
        }

        MedicalNoteDetailsDto dto = new MedicalNoteDetailsDto();

        dto.setId(entity.getId());
        dto.setNoteType(entity.getNoteType());
        dto.setContent(entity.getContent());
        dto.setDate(entity.getDate());
        dto.setCreatedAt(entity.getCreatedAt());

        if (entity.getAppointment() != null) {
            Appointment appointment = entity.getAppointment();
            dto.setAppointmentId(appointment.getId());
            dto.setAppointmentDate(appointment.getDate());
            dto.setAppointmentTime(appointment.getTime());
        }

        if (entity.getMedicalStaff() != null) {
            MedicalStaff staff = entity.getMedicalStaff();
            dto.setMedicalStaffId(staff.getId());
            dto.setMedicalStaffFullName(staff.getEmployee().getUser().getFirstName() + " " + staff.getEmployee().getUser().getSurname());
            dto.setMedicalStaffProfession(staff.getProfession().name());
        }

        return dto;
    }


    public List<MedicalNoteDetailsDto> toDetailsDtoList(List<MedicalNote> entities) {
        return entities.stream()
                .map(this::toDetailsDto)
                .collect(Collectors.toList());
    }

    public MedicalNote toEntity(MedicalNoteRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return MedicalNote.builder()
                .content(dto.getContent())
                .date(dto.getDate())
                .noteType(dto.getNoteType())
                .build();
    }

    public void updateEntity(MedicalNote entity, MedicalNoteRequestDto dto) {
        if (dto.getContent() != null) {
            entity.setContent(dto.getContent());
        }
        if (dto.getDate() != null) {
            entity.setDate(dto.getDate());
        }
        if (dto.getNoteType() != null) {
            entity.setNoteType(dto.getNoteType());
        }

    }








}
