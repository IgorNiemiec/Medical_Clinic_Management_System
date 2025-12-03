package org.example.medical_clinic_management_system.controller.record;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.DiseaseCourseDetailsDto;
import org.example.medical_clinic_management_system.dto.record.DiseaseCourseRequestDto;
import org.example.medical_clinic_management_system.service.record.DiseaseCourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disease-courses")
@RequiredArgsConstructor
public class DiseaseCourseController
{

    private final DiseaseCourseService diseaseCourseService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public ResponseEntity<DiseaseCourseDetailsDto> createDiseaseCourse(@Valid @RequestBody DiseaseCourseRequestDto dto) {
        DiseaseCourseDetailsDto newCourse = diseaseCourseService.createDiseaseCourse(dto);
        return new ResponseEntity<>(newCourse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public ResponseEntity<DiseaseCourseDetailsDto> getDiseaseCourseById(@PathVariable Long id) {
        DiseaseCourseDetailsDto course = diseaseCourseService.getDiseaseCourseById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/history")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public ResponseEntity<List<DiseaseCourseDetailsDto>> getHistoryByPatientCardId(@RequestParam Long patientCardId) {
        List<DiseaseCourseDetailsDto> history = diseaseCourseService.getHistoryByPatientCardId(patientCardId);
        return ResponseEntity.ok(history);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public ResponseEntity<DiseaseCourseDetailsDto> updateDiseaseCourse(@PathVariable Long id, @Valid @RequestBody DiseaseCourseRequestDto dto) {
        DiseaseCourseDetailsDto updatedCourse = diseaseCourseService.updateDiseaseCourse(id, dto);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('DOCTOR')")
    public ResponseEntity<Void> deleteDiseaseCourse(@PathVariable Long id) {
        diseaseCourseService.deleteDiseaseCourse(id);
        return ResponseEntity.noContent().build();
    }


}
