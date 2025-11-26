package org.example.medical_clinic_management_system.controller.medicalService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medicalService.AppointmentMedicalServiceDetailsDto;
import org.example.medical_clinic_management_system.dto.medicalService.AppointmentMedicalServiceRequestDto;
import org.example.medical_clinic_management_system.service.medicalService.AppointmentMedicalServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/appointment-medical-services")
@RequiredArgsConstructor
public class AppointmentMedicalServiceController
{

    private final AppointmentMedicalServiceService appointmentMedicalServiceService;

    @PostMapping
    public ResponseEntity<AppointmentMedicalServiceDetailsDto> addServiceToAppointment(@Valid @RequestBody AppointmentMedicalServiceRequestDto requestDto) {
        AppointmentMedicalServiceDetailsDto newService = appointmentMedicalServiceService.addServiceToAppointment(requestDto);
        return new ResponseEntity<>(newService, HttpStatus.CREATED);
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<AppointmentMedicalServiceDetailsDto>> getServicesByAppointmentId(@PathVariable Long appointmentId) {
        List<AppointmentMedicalServiceDetailsDto> services = appointmentMedicalServiceService.getServicesByAppointmentId(appointmentId);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentMedicalServiceDetailsDto> getAppointmentServiceById(@PathVariable Long id) {
        AppointmentMedicalServiceDetailsDto service = appointmentMedicalServiceService.getAppointmentServiceById(id);
        return ResponseEntity.ok(service);
    }

    @GetMapping("/cost/{appointmentId}")
    public ResponseEntity<BigDecimal> calculateTotalCost(@PathVariable Long appointmentId) {
        BigDecimal totalCost = appointmentMedicalServiceService.calculateTotalCost(appointmentId);
        return ResponseEntity.ok(totalCost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointmentService(@PathVariable Long id) {
        appointmentMedicalServiceService.deleteAppointmentService(id);
        return ResponseEntity.noContent().build();
    }



}
