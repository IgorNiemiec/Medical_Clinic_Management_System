package org.example.medical_clinic_management_system.controller.visit;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.AppointmentDetailsDto;
import org.example.medical_clinic_management_system.dto.visit.AppointmentRequestDto;
import org.example.medical_clinic_management_system.service.visit.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController
{

    private final AppointmentService appointmentService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<AppointmentDetailsDto> createAppointment(@Valid @RequestBody AppointmentRequestDto dto) {

        AppointmentDetailsDto newAppointment = appointmentService.createAppointment(dto);
        return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<AppointmentDetailsDto> getAppointmentById(@PathVariable Long id) {
        AppointmentDetailsDto appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/schedule/staff/{medicalStaffId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<List<AppointmentDetailsDto>> getStaffScheduleForDay(
            @PathVariable Long medicalStaffId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<AppointmentDetailsDto> schedule = appointmentService.getStaffScheduleForDay(medicalStaffId, date);
        return ResponseEntity.ok(schedule);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<AppointmentDetailsDto> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentRequestDto dto) {
        AppointmentDetailsDto updatedAppointment = appointmentService.updateAppointment(id, dto);
        return ResponseEntity.ok(updatedAppointment);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('RECEPTIONIST')")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }





}
