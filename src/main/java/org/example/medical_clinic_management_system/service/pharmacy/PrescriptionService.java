package org.example.medical_clinic_management_system.service.pharmacy;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.pharmacy.PrescriptionDetailsDto;
import org.example.medical_clinic_management_system.dto.pharmacy.PrescriptionRequestDto;
import org.example.medical_clinic_management_system.mapper.pharmacy.PrescriptionMapper;
import org.example.medical_clinic_management_system.model.pharmacy.Prescription;
import org.example.medical_clinic_management_system.repository.person.MedicalStaffRepository;
import org.example.medical_clinic_management_system.repository.person.PatientRepository;
import org.example.medical_clinic_management_system.repository.pharmacy.DrugRepository;
import org.example.medical_clinic_management_system.repository.pharmacy.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionService
{

    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;
    private final MedicalStaffRepository medicalStaffRepository;
    private final DrugRepository drugRepository;

    private final PrescriptionMapper prescriptionMapper;


    @Transactional
    public PrescriptionDetailsDto createPrescription(PrescriptionRequestDto requestDto) {

        // 1. Walidacja i pobranie powiązanych encji
        var patient = patientRepository.findById(requestDto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + requestDto.getPatientId()));


        var doctor = medicalStaffRepository.findById(requestDto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor (MedicalStaff) not found with ID: " + requestDto.getDoctorId()));


        var drug = drugRepository.findById(requestDto.getDrugId())
                .orElseThrow(() -> new RuntimeException("Drug not found with ID: " + requestDto.getDrugId()));

        Prescription prescription = prescriptionMapper.toEntity(requestDto);

        prescription.setPatient(patient);
        prescription.setDoctor(doctor);
        prescription.setDrug(drug);

        Prescription savedPrescription = prescriptionRepository.save(prescription);

        return prescriptionMapper.toDetailsDto(savedPrescription);
    }


    @Transactional
    public PrescriptionDetailsDto getPrescriptionById(Long prescriptionId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RuntimeException("Prescription not found with ID: " + prescriptionId));

        return prescriptionMapper.toDetailsDto(prescription);
    }

    @Transactional
    public List<PrescriptionDetailsDto> getPrescriptionsByPatient(Long patientId) {

        if (!patientRepository.existsById(patientId)) {
            throw new RuntimeException("Patient not found with ID: " + patientId);
        }

        List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);

        return prescriptionMapper.toDetailsDtoList(prescriptions);
    }

    @Transactional
    public List<PrescriptionDetailsDto> getPrescriptionsByDoctor(Long doctorId) {

        if (!medicalStaffRepository.existsById(doctorId)) {
            throw new RuntimeException("Doctor (MedicalStaff) not found with ID: " + doctorId);
        }


        List<Prescription> prescriptions = prescriptionRepository.findByDoctorId(doctorId);

        return prescriptionMapper.toDetailsDtoList(prescriptions);
    }

    @Transactional
    public void deletePrescription(Long prescriptionId) {
        if (!prescriptionRepository.existsById(prescriptionId)) {
            throw new RuntimeException("Prescription not found with ID: " + prescriptionId);
        }

        prescriptionRepository.deleteById(prescriptionId);
    }




}
