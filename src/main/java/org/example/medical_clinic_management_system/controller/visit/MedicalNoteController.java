package org.example.medical_clinic_management_system.controller.visit;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.MedicalNoteDetailsDto;
import org.example.medical_clinic_management_system.dto.visit.MedicalNoteDto;
import org.example.medical_clinic_management_system.dto.visit.MedicalNoteRequestDto;
import org.example.medical_clinic_management_system.service.visit.MedicalNoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-notes")
@RequiredArgsConstructor
public class MedicalNoteController
{

    private final MedicalNoteService medicalNoteService;

    @PostMapping
    public ResponseEntity<MedicalNoteDetailsDto> createMedicalNote(@Valid @RequestBody MedicalNoteRequestDto dto) {
        MedicalNoteDetailsDto newNote = medicalNoteService.createMedicalNote(dto);
        return new ResponseEntity<>(newNote, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalNoteDetailsDto> getMedicalNoteById(@PathVariable Long id) {
        MedicalNoteDetailsDto note = medicalNoteService.getMedicalNoteById(id);
        return ResponseEntity.ok(note);
    }

    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<List<MedicalNoteDetailsDto>> getNotesByAppointmentId(@PathVariable Long appointmentId) {
        List<MedicalNoteDetailsDto> notes = medicalNoteService.getNotesByAppointmentId(appointmentId);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<MedicalNoteDetailsDto>> getNotesByPatientId(@PathVariable Long patientId) {
        List<MedicalNoteDetailsDto> notes = medicalNoteService.getNotesByPatientId(patientId);
        return ResponseEntity.ok(notes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalNoteDetailsDto> updateMedicalNote(@PathVariable Long id, @Valid @RequestBody MedicalNoteRequestDto dto) {
        MedicalNoteDetailsDto updatedNote = medicalNoteService.updateMedicalNote(id, dto);
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalNote(@PathVariable Long id) {
        medicalNoteService.deleteMedicalNote(id);
        return ResponseEntity.noContent().build();
    }



}
