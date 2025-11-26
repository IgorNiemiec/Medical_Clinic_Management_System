package org.example.medical_clinic_management_system.service.record;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.record.DiseaseCourseDetailsDto;
import org.example.medical_clinic_management_system.dto.record.DiseaseCourseRequestDto;
import org.example.medical_clinic_management_system.mapper.record.DiseaseCourseMapper;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.record.DiseaseCourse;
import org.example.medical_clinic_management_system.model.record.ICDCode;
import org.example.medical_clinic_management_system.model.record.PatientCard;
import org.example.medical_clinic_management_system.repository.person.MedicalStaffRepository;
import org.example.medical_clinic_management_system.repository.record.DiseaseCourseRepository;
import org.example.medical_clinic_management_system.repository.record.ICDCodeRepository;
import org.example.medical_clinic_management_system.repository.record.PatientCardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiseaseCourseService
{

    private final DiseaseCourseRepository diseaseCourseRepository;
    private final DiseaseCourseMapper diseaseCourseMapper;

    private final PatientCardRepository patientCardRepository;
    private final ICDCodeRepository icdCodeRepository;
    private final MedicalStaffRepository medicalStaffRepository;


    @Transactional
    public DiseaseCourseDetailsDto createDiseaseCourse(DiseaseCourseRequestDto requestDto) {


        PatientCard patientCard = patientCardRepository.findById(requestDto.getPatientCardId())
                .orElseThrow(() -> new RuntimeException("Karta Pacjenta o ID: " + requestDto.getPatientCardId() + " nie została znaleziona."));

        ICDCode icdCode = icdCodeRepository.findById(requestDto.getIcdCodeId())
                .orElseThrow(() -> new RuntimeException("Kod ICD o ID: " + requestDto.getIcdCodeId() + " nie został znaleziony."));

        MedicalStaff medicalStaff = medicalStaffRepository.findById(requestDto.getMedicalStaffId())
                .orElseThrow(() -> new RuntimeException("Personel Medyczny o ID: " + requestDto.getMedicalStaffId() + " nie został znaleziony."));


        DiseaseCourse diseaseCourse = diseaseCourseMapper.toEntity(requestDto);


        diseaseCourse.setPatientCard(patientCard);
        diseaseCourse.setIcdCode(icdCode);
        diseaseCourse.setMedicalStaff(medicalStaff);


        if (requestDto.getDiagnosisDate() != null) {
            diseaseCourse.setDiagnosisDate(requestDto.getDiagnosisDate());
        }

        DiseaseCourse savedCourse = diseaseCourseRepository.save(diseaseCourse);


        patientCard.setLastUpdate(LocalDateTime.now());
        patientCardRepository.save(patientCard);

        return diseaseCourseMapper.toDetailsDto(savedCourse);
    }


    @Transactional
    public DiseaseCourseDetailsDto getDiseaseCourseById(Long id) {
        DiseaseCourse entity = diseaseCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wpis przebiegu choroby o ID: " + id + " nie został znaleziony."));
        return diseaseCourseMapper.toDetailsDto(entity);
    }

    @Transactional
    public List<DiseaseCourseDetailsDto> getHistoryByPatientCardId(Long patientCardId) {
        List<DiseaseCourse> history = diseaseCourseRepository.findByPatientCardIdOrderByDiagnosisDateDesc(patientCardId);
        return diseaseCourseMapper.toDetailsDtoList(history);
    }

    @Transactional
    public DiseaseCourseDetailsDto updateDiseaseCourse(Long id, DiseaseCourseRequestDto requestDto) {
        DiseaseCourse entity = diseaseCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wpis przebiegu choroby o ID: " + id + " nie został znaleziony."));


        if (!entity.getIcdCode().getId().equals(requestDto.getIcdCodeId())) {
            ICDCode newIcdCode = icdCodeRepository.findById(requestDto.getIcdCodeId())
                    .orElseThrow(() -> new RuntimeException("Kod ICD o ID: " + requestDto.getIcdCodeId() + " nie został znaleziony."));
            entity.setIcdCode(newIcdCode);
        }

        if (!entity.getMedicalStaff().getId().equals(requestDto.getMedicalStaffId())) {
            MedicalStaff newStaff = medicalStaffRepository.findById(requestDto.getMedicalStaffId())
                    .orElseThrow(() -> new RuntimeException("Personel Medyczny o ID: " + requestDto.getMedicalStaffId() + " nie został znaleziony."));
            entity.setMedicalStaff(newStaff);
        }

        if (!entity.getPatientCard().getId().equals(requestDto.getPatientCardId())) {
            throw new RuntimeException("Nie można zmienić karty pacjenta dla istniejącego wpisu przebiegu choroby.");
        }

        diseaseCourseMapper.updateEntity(entity, requestDto);
        DiseaseCourse updatedCourse = diseaseCourseRepository.save(entity);

        entity.getPatientCard().setLastUpdate(LocalDateTime.now());
        patientCardRepository.save(entity.getPatientCard());

        return diseaseCourseMapper.toDetailsDto(updatedCourse);
    }

    @Transactional
    public void deleteDiseaseCourse(Long id) {
        DiseaseCourse entity = diseaseCourseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wpis przebiegu choroby o ID: " + id + " nie został znaleziony."));

        diseaseCourseRepository.delete(entity);

        PatientCard card = entity.getPatientCard();
        card.setLastUpdate(LocalDateTime.now());
        patientCardRepository.save(card);
    }




}
