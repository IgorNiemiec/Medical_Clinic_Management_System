package org.example.medical_clinic_management_system.service.record;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.PatientCardDetailsDto;
import org.example.medical_clinic_management_system.dto.record.PatientCardDto;
import org.example.medical_clinic_management_system.dto.record.PatientCardRequestDto;
import org.example.medical_clinic_management_system.mapper.record.PatientCardMapper;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.record.PatientCard;
import org.example.medical_clinic_management_system.repository.person.PatientRepository;
import org.example.medical_clinic_management_system.repository.record.PatientCardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientCardService
{

    private final PatientCardRepository patientCardRepository;
    private final PatientCardMapper patientCardMapper;
    private final PatientRepository patientRepository;


    @Transactional
    public PatientCardDetailsDto createPatientCard(PatientCardRequestDto requestDto) {


        Patient patient = patientRepository.findById(requestDto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Pacjent o ID: " + requestDto.getPatientId() + " nie został znaleziony."));


        if (patientCardRepository.existsByPatientId(requestDto.getPatientId())) {
            throw new IllegalStateException("Pacjent o ID: " + requestDto.getPatientId() + " już posiada Kartę Pacjenta.");
        }

        PatientCard patientCard = patientCardMapper.toEntity(requestDto);
        patientCard.setPatient(patient);

        patientCard.setLastUpdate(patientCard.getCreatedAt());

        PatientCard savedCard = patientCardRepository.save(patientCard);

        return patientCardMapper.toDetailsDto(savedCard);
    }


    @Transactional
    public PatientCardDetailsDto getPatientCardById(Long cardId) {
        PatientCard entity = patientCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Karta Pacjenta o ID: " + cardId + " nie została znaleziona."));

        return patientCardMapper.toDetailsDto(entity);
    }

    @Transactional
    public PatientCardDetailsDto getPatientCardByPatientId(Long patientId) {
        PatientCard entity = patientCardRepository.findByPatientId(patientId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono Karty Pacjenta dla Pacjenta o ID: " + patientId + "."));

        return patientCardMapper.toDetailsDto(entity);
    }

    @Transactional
    public void deletePatientCard(Long cardId) {
        if (!patientCardRepository.existsById(cardId)) {
            throw new RuntimeException("Nie można usunąć. Karta Pacjenta o ID: " + cardId + " nie istnieje.");
        }
        patientCardRepository.deleteById(cardId);
    }








}
