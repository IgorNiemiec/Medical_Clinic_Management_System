package org.example.medical_clinic_management_system.service.medicalService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.medicalService.MedicalServiceDetailsDto;
import org.example.medical_clinic_management_system.dto.medicalService.MedicalServiceRequestDto;
import org.example.medical_clinic_management_system.mapper.medicalService.MedicalServiceMapper;
import org.example.medical_clinic_management_system.model.medicalService.MedicalService;
import org.example.medical_clinic_management_system.repository.medicalService.MedicalServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalServiceService
{

    private final MedicalServiceRepository medicalServiceRepository;
    private final MedicalServiceMapper medicalServiceMapper;
    private final AppointmentMedicalServiceService appointmentMedicalServiceRepository;

    private void validateUniqueTitle(String title, Long currentId) {
        if (medicalServiceRepository.existsByTitleIgnoreCase(title)) {
            MedicalService existingService = medicalServiceRepository.findByTitleIgnoreCase(title)
                    .orElseThrow(() -> new IllegalStateException("Nie znaleziono usługi, mimo że istnieje w bazie (błąd logiki)."));

            if (currentId == null || !existingService.getId().equals(currentId)) {
                throw new IllegalArgumentException("Usługa o tytule: '" + title + "' już istnieje w katalogu.");
            }
        }
    }

    @Transactional
    public MedicalServiceDetailsDto createMedicalService(MedicalServiceRequestDto requestDto) {

        validateUniqueTitle(requestDto.getTitle(), null);

        MedicalService service = medicalServiceMapper.toEntity(requestDto);
        MedicalService savedService = medicalServiceRepository.save(service);
        return medicalServiceMapper.toDetailsDto(savedService);
    }

    @Transactional
    public MedicalServiceDetailsDto getMedicalServiceById(Long id) {
        MedicalService service = medicalServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usługa Medyczna"));
        return medicalServiceMapper.toDetailsDto(service);
    }

    @Transactional
    public List<MedicalServiceDetailsDto> getAllMedicalServices() {
        List<MedicalService> services = medicalServiceRepository.findAll();
        return medicalServiceMapper.toDetailsDtoList(services);
    }


    @Transactional
    public List<MedicalServiceDetailsDto> searchMedicalServicesByTitle(String titleFragment) {
        List<MedicalService> services = medicalServiceRepository.findByTitleContainingIgnoreCaseOrderByTitleAsc(titleFragment);
        return medicalServiceMapper.toDetailsDtoList(services);
    }

    @Transactional
    public MedicalServiceDetailsDto updateMedicalService(Long id, MedicalServiceRequestDto requestDto) {
        MedicalService existingService = medicalServiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usługa Medyczna"));


        if (!existingService.getTitle().equalsIgnoreCase(requestDto.getTitle())) {
            validateUniqueTitle(requestDto.getTitle(), id);
        }

        medicalServiceMapper.updateEntity(existingService, requestDto);
        MedicalService updatedService = medicalServiceRepository.save(existingService);
        return medicalServiceMapper.toDetailsDto(updatedService);
    }

    @Transactional
    public void deleteMedicalService(Long id) {
        if (!medicalServiceRepository.existsById(id)) {
            throw new RuntimeException("Usługa Medyczna");
        }

        // TODO: W realnym systemie należałoby sprawdzić, czy usługa jest używana w AppointmentMedicalService
        medicalServiceRepository.deleteById(id);
    }







}
