package org.example.medical_clinic_management_system.service.visit;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.MedicalNoteDto;
import org.example.medical_clinic_management_system.mapper.visit.MedicalNoteMapper;
import org.example.medical_clinic_management_system.model.visit.MedicalNote;
import org.example.medical_clinic_management_system.repository.visit.MedicalNoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalNoteService
{

    private final MedicalNoteRepository medicalNoteRepository;
    private final MedicalNoteMapper medicalNoteMapper;

    public List<MedicalNoteDto> getAll() {
        return medicalNoteRepository.findAll()
                .stream()
                .map(medicalNoteMapper::toDto)
                .collect(Collectors.toList());
    }

    public MedicalNoteDto getById(Long id) {
        return medicalNoteMapper.toDto(
                medicalNoteRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Medical note not found"))
        );
    }

    public MedicalNoteDto create(MedicalNoteDto dto) {
        MedicalNote note = medicalNoteMapper.toEntity(dto);
        return medicalNoteMapper.toDto(medicalNoteRepository.save(note));
    }

    public MedicalNoteDto update(Long id, MedicalNoteDto dto) {
        MedicalNote existing = medicalNoteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medical note not found"));
        MedicalNote updated = medicalNoteMapper.toEntity(dto);
        updated.setId(existing.getId());
        return medicalNoteMapper.toDto(medicalNoteRepository.save(updated));
    }

    public void delete(Long id) {
        medicalNoteRepository.deleteById(id);
    }


}
