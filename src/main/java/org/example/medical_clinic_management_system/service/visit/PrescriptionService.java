package org.example.medical_clinic_management_system.service.visit;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.pharmacy.PrescriptionDetailsDto;
import org.example.medical_clinic_management_system.dto.pharmacy.PrescriptionRequestDto;
import org.example.medical_clinic_management_system.dto.visit.PrescriptionDto;
import org.example.medical_clinic_management_system.mapper.pharmacy.PrescriptionMapper;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.Patient;
import org.example.medical_clinic_management_system.model.pharmacy.Drug;
import org.example.medical_clinic_management_system.model.pharmacy.Prescription;
import org.example.medical_clinic_management_system.repository.person.MedicalStaffRepository;
import org.example.medical_clinic_management_system.repository.person.PatientRepository;
import org.example.medical_clinic_management_system.repository.pharmacy.DrugRepository;
import org.example.medical_clinic_management_system.repository.pharmacy.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionService
{

    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;
    private final MedicalStaffRepository medicalStaffRepository;
    private final DrugRepository drugRepository;
    private final PrescriptionMapper prescriptionMapper;


    private Patient findPatient(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new NoSuchElementException("Patient not found with ID: " + patientId));
    }


    private MedicalStaff findDoctor(Long doctorId) {
        return medicalStaffRepository.findById(doctorId)
                .orElseThrow(() -> new NoSuchElementException("Doctor not found with ID: " + doctorId));
    }

    private Drug findDrug(Long drugId) {
        return drugRepository.findById(drugId)
                .orElseThrow(() -> new NoSuchElementException("Drug not found with ID: " + drugId));
    }


    @Transactional
    public PrescriptionDetailsDto create(PrescriptionRequestDto dto) {

        Patient patient = findPatient(dto.getPatientId());
        MedicalStaff doctor = findDoctor(dto.getDoctorId());
        Drug drug = findDrug(dto.getDrugId());


        Prescription newPrescription = prescriptionMapper.toEntity(dto);
        newPrescription.setPatient(patient);
        newPrescription.setDoctor(doctor);
        newPrescription.setDrug(drug);

        if (newPrescription.getExpirationDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiration date must be in the future.");
        }

        Prescription savedPrescription = prescriptionRepository.save(newPrescription);

        return prescriptionMapper.toDetailsDto(savedPrescription);
    }


    @Transactional
    public PrescriptionDetailsDto findById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Prescription not found with ID: " + id));

        return prescriptionMapper.toDetailsDto(prescription);
    }


    @Transactional
    public List<PrescriptionDetailsDto> findByPatient(Long patientId) {
        List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
        return prescriptionMapper.toDetailsDtoList(prescriptions);
    }


    @Transactional
    public List<PrescriptionDetailsDto> findByDoctor(Long doctorId) {
        List<Prescription> prescriptions = prescriptionRepository.findByDoctorId(doctorId);
        return prescriptionMapper.toDetailsDtoList(prescriptions);
    }

    @Transactional
    public List<PrescriptionDetailsDto> findExpiredBefore(LocalDate date) {
        List<Prescription> prescriptions = prescriptionRepository.findByExpirationDateBefore(date);
        return prescriptionMapper.toDetailsDtoList(prescriptions);
    }

    @Transactional
    public PrescriptionDetailsDto update(Long id, PrescriptionRequestDto dto) {

        Prescription existingPrescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Prescription not found with ID: " + id));


        Patient patient = findPatient(dto.getPatientId());
        MedicalStaff doctor = findDoctor(dto.getDoctorId());
        Drug drug = findDrug(dto.getDrugId());

        existingPrescription.setPatient(patient);
        existingPrescription.setDoctor(doctor);
        existingPrescription.setDrug(drug);
        existingPrescription.setExpirationDate(dto.getExpirationDate());
        existingPrescription.setDosageInstructions(dto.getDosageInstructions());
        existingPrescription.setQuantity(dto.getQuantity());

        if (existingPrescription.getExpirationDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expiration date must be in the future.");
        }

        Prescription updatedPrescription = prescriptionRepository.save(existingPrescription);
        return prescriptionMapper.toDetailsDto(updatedPrescription);
    }


    @Transactional
    public void deleteById(Long id) {
        if (!prescriptionRepository.existsById(id)) {
            throw new NoSuchElementException("Prescription not found with ID: " + id);
        }
        prescriptionRepository.deleteById(id);
    }


}
