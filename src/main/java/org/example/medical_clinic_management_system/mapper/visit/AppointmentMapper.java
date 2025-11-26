package org.example.medical_clinic_management_system.mapper.visit;

import org.example.medical_clinic_management_system.dto.visit.AppointmentDetailsDto;
import org.example.medical_clinic_management_system.dto.visit.AppointmentRequestDto;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.visit.Appointment;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper
{

    public AppointmentDetailsDto toDetailsDto(Appointment entity) {

        if (entity == null)
        {
            return null;
        }

        AppointmentDetailsDto dto = new AppointmentDetailsDto();


        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setTime(entity.getTime());
        dto.setType(entity.getType());
        dto.setStatus(entity.getStatus());
        dto.setDescription(entity.getDescription());

        if (entity.getPatient() != null) {
            Patient patient = entity.getPatient();
            dto.setPatientId(patient.getId());
            dto.setPatientFullName(patient.getUser().getFirstName() + " " + patient.getUser().getSurname());
        }

        if (entity.getMedicalStaff() != null) {
            MedicalStaff staff = entity.getMedicalStaff();
            dto.setMedicalStaffId(staff.getId());
            dto.setMedicalStaffFullName(staff.getEmployee().getUser().getFirstName() + " " + staff.getEmployee().getUser().getSurname());
            dto.setMedicalStaffProfession(staff.getProfession().name());
        }

        if (entity.getExaminationRoom() != null) {
            ExaminationRoom room = entity.getExaminationRoom();
            dto.setExaminationRoomId(room.getId());
            dto.setExaminationRoomNumber(room.getNumber());
        }

        return dto;
    }


    public List<AppointmentDetailsDto> toDetailsDtoList(List<Appointment> entities) {
        return entities.stream()
                .map(this::toDetailsDto)
                .collect(Collectors.toList());
    }

    public Appointment toEntity(AppointmentRequestDto dto) {
        if (dto == null) {
            return null;
        }

        return Appointment.builder()
                .date(dto.getDate())
                .time(dto.getTime())
                .type(dto.getType())
                .status(dto.getStatus())
                .description(dto.getDescription())
                .build();
    }

    public void updateEntity(Appointment entity, AppointmentRequestDto dto)
    {
        if (dto.getDate() != null) {
            entity.setDate(dto.getDate());
        }
        if (dto.getTime() != null) {
            entity.setTime(dto.getTime());
        }
        if (dto.getType() != null) {
            entity.setType(dto.getType());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }

    }




}
