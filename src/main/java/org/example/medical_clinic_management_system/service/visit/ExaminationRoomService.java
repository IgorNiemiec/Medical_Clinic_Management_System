package org.example.medical_clinic_management_system.service.visit;

import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.ExaminationRoomDto;
import org.example.medical_clinic_management_system.mapper.visit.ExaminationRoomMapper;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;
import org.example.medical_clinic_management_system.repository.visit.ExaminationRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExaminationRoomService
{

    private final ExaminationRoomRepository examinationRoomRepository;
    private final ExaminationRoomMapper examinationRoomMapper;

    public List<ExaminationRoomDto> getAll() {
        return examinationRoomRepository.findAll()
                .stream()
                .map(examinationRoomMapper::toDto)
                .collect(Collectors.toList());
    }

    public ExaminationRoomDto getById(Long id) {
        return examinationRoomMapper.toDto(
                examinationRoomRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Examination room not found"))
        );
    }

    public ExaminationRoomDto create(ExaminationRoomDto dto) {
        ExaminationRoom room = examinationRoomMapper.toEntity(dto);
        return examinationRoomMapper.toDto(examinationRoomRepository.save(room));
    }

    public ExaminationRoomDto update(Long id, ExaminationRoomDto dto) {
        ExaminationRoom existing = examinationRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Examination room not found"));
        ExaminationRoom updated = examinationRoomMapper.toEntity(dto);
        updated.setId(existing.getId());
        return examinationRoomMapper.toDto(examinationRoomRepository.save(updated));
    }

    public void delete(Long id) {
        examinationRoomRepository.deleteById(id);
    }


}
