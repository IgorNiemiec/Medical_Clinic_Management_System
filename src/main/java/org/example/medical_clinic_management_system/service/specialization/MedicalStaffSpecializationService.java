package org.example.medical_clinic_management_system.service.specialization;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.specialization.SpecializationAssignmentRequestDTO;
import org.example.medical_clinic_management_system.dto.specialization.StaffSpecializationDetailsDTO;
import org.example.medical_clinic_management_system.mapper.specialization.MedicalStaffSpecializationMapper;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.specialization.MedicalStaffSpecialization;
import org.example.medical_clinic_management_system.model.specialization.MedicalStaffSpecializationId;
import org.example.medical_clinic_management_system.model.specialization.Specialization;
import org.example.medical_clinic_management_system.repository.person.MedicalStaffRepository;
import org.example.medical_clinic_management_system.repository.person.SpecializationRepository;
import org.example.medical_clinic_management_system.repository.specialization.MedicalStaffSpecializationRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalStaffSpecializationService
{

    private final MedicalStaffRepository staffRepository;
    private final SpecializationRepository specializationRepository;
    private final MedicalStaffSpecializationRepository assignmentRepository;
    private final MedicalStaffSpecializationMapper assignmentMapper;

    @Transactional
    public StaffSpecializationDetailsDTO assignSpecialization(SpecializationAssignmentRequestDTO requestDto) {


        MedicalStaff staff = staffRepository.findById(requestDto.getStaffId())
                .orElseThrow(() -> new RuntimeException("MedicalStaff not found with ID: " + requestDto.getStaffId()));

        Specialization specialization = specializationRepository.findById(requestDto.getSpecializationId())
                .orElseThrow(() -> {
                    return new RuntimeException("Specialization not found with ID: " + requestDto.getSpecializationId());
                });


        MedicalStaffSpecializationId checkId = new MedicalStaffSpecializationId(staff.getId(), specialization.getId());
        if (assignmentRepository.existsById(checkId)) {

            throw new IllegalStateException("Specialization already assigned to this staff member.");
        }


        MedicalStaffSpecialization assignment = new MedicalStaffSpecialization();


        assignment.setId(checkId);


        assignment.setMedicalStaff(staff);
        assignment.setSpecialization(specialization);


        assignment.setDateCertified(requestDto.getDateCertified());


        MedicalStaffSpecialization savedAssignment = assignmentRepository.save(assignment);


        return assignmentMapper.toDetailsDto(savedAssignment);
    }

    @Transactional
    public List<StaffSpecializationDetailsDTO> getStaffSpecializations(Long staffId) {

        if (!staffRepository.existsById(staffId)) {
            throw new RuntimeException("MedicalStaff not found with ID: " + staffId);
        }

        List<MedicalStaffSpecialization> assignments = assignmentRepository.findByMedicalStaffId(staffId);

        return assignmentMapper.toDetailsDtoList(assignments);
    }

    @Transactional
    public void removeSpecialization(Long staffId, Long specializationId) {
        MedicalStaffSpecializationId id = new MedicalStaffSpecializationId(staffId, specializationId);

        if (!assignmentRepository.existsById(id)) {

            throw new RuntimeException("Specialization assignment not found for given IDs.");
        }

        assignmentRepository.deleteById(id);
    }


}
