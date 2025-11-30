package org.example.medical_clinic_management_system.service.visit;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.visit.ExaminationRoomDetailsDto;
import org.example.medical_clinic_management_system.dto.visit.ExaminationRoomRequestDto;
import org.example.medical_clinic_management_system.mapper.visit.ExaminationRoomMapper;
import org.example.medical_clinic_management_system.model.visit.ExaminationRoom;
import org.example.medical_clinic_management_system.repository.visit.ExaminationRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExaminationRoomService
{

    private final ExaminationRoomRepository examinationRoomRepository;
    private final ExaminationRoomMapper examinationRoomMapper;

    @Transactional
    public List<ExaminationRoomDetailsDto> getAllRooms() {
        List<ExaminationRoom> entities = examinationRoomRepository.findAll();
        return examinationRoomMapper.toDetailsDtoList(entities);
    }


    @Transactional
    public List<ExaminationRoomDetailsDto> getRoomsByStatus(ExaminationRoom.ExaminationRoomStatus status) {
        List<ExaminationRoom> entities = examinationRoomRepository.findByStatus(status);
        return examinationRoomMapper.toDetailsDtoList(entities);
    }

    @Transactional
    public ExaminationRoomDetailsDto getRoomById(Long id) {
        ExaminationRoom entity = examinationRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gabinet o ID: " + id + " nie został znaleziony."));
        return examinationRoomMapper.toDetailsDto(entity);
    }

    @Transactional
    public ExaminationRoomDetailsDto createRoom(ExaminationRoomRequestDto requestDto) {

        examinationRoomRepository.findByNumber(requestDto.getNumber()).ifPresent(room -> {
            throw new IllegalStateException("Gabinet o numerze: " + requestDto.getNumber() + " już istnieje.");
        });

        ExaminationRoom entity = examinationRoomMapper.toEntity(requestDto);
        ExaminationRoom savedEntity = examinationRoomRepository.save(entity);

        return examinationRoomMapper.toDetailsDto(savedEntity);
    }

    @Transactional
    public ExaminationRoomDetailsDto updateRoom(Long id, ExaminationRoomRequestDto requestDto) {
        ExaminationRoom entity = examinationRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gabinet o ID: " + id + " nie został znaleziony."));


        if (requestDto.getNumber() != null && !requestDto.getNumber().equals(entity.getNumber())) {
            examinationRoomRepository.findByNumber(requestDto.getNumber()).ifPresent(room -> {
                if (!room.getId().equals(id)) {
                    throw new IllegalStateException("Gabinet o numerze: " + requestDto.getNumber() + " już istnieje w innym rekordzie.");
                }
            });
        }

        examinationRoomMapper.updateEntity(entity, requestDto);
        ExaminationRoom updatedEntity = examinationRoomRepository.save(entity);

        return examinationRoomMapper.toDetailsDto(updatedEntity);
    }

    @Transactional
    public ExaminationRoomDetailsDto updateRoomStatus(Long id, ExaminationRoom.ExaminationRoomStatus newStatus) {
        ExaminationRoom entity = examinationRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gabinet o ID: " + id + " nie został znaleziony."));

        entity.setStatus(newStatus);
        ExaminationRoom updatedEntity = examinationRoomRepository.save(entity);

        return examinationRoomMapper.toDetailsDto(updatedEntity);
    }

    @Transactional
    public void deleteRoom(Long id) {
        if (!examinationRoomRepository.existsById(id)) {
            throw new RuntimeException("Nie można usunąć. Gabinet o ID: " + id + " nie istnieje.");
        }
        // TODO: systemie należy sprawdzić, czy gabinet nie jest aktualnie powiązany z Appointment.

        examinationRoomRepository.deleteById(id);
    }



}
