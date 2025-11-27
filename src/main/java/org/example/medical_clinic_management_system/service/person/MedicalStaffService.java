package org.example.medical_clinic_management_system.service.person;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffDetailsDto;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffDto;
import org.example.medical_clinic_management_system.dto.person.MedicalStaffRequestDto;
import org.example.medical_clinic_management_system.mapper.person.MedicalStaffMapper;
import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.repository.person.EmployeeRepository;
import org.example.medical_clinic_management_system.repository.person.MedicalStaffRepository;
import org.example.medical_clinic_management_system.repository.person.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalStaffService
{

    private final MedicalStaffRepository medicalStaffRepository;
    private final EmployeeRepository employeeRepository;
    private final MedicalStaffMapper medicalStaffMapper;

    private static final String MEDICAL_STAFF_NOT_FOUND = "MedicalStaff not found with ID: ";
    private static final String EMPLOYEE_NOT_FOUND = "Employee not found with ID: ";
    private static final String LICENSE_NUMBER_EXISTS = "License number already exists: ";

    @Transactional
    public List<MedicalStaffDetailsDto> findAll() {
        return medicalStaffRepository.findAll().stream()
                .map(medicalStaffMapper::toDetailsDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public MedicalStaffDetailsDto findById(Long id) {
        MedicalStaff medicalStaff = medicalStaffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MEDICAL_STAFF_NOT_FOUND + id));
        return medicalStaffMapper.toDetailsDto(medicalStaff);
    }

    @Transactional
    public MedicalStaffDetailsDto create(MedicalStaffRequestDto dto) {

        if (medicalStaffRepository.findByLicenseNumber(dto.getLicenseNumber()).isPresent()) {
            throw new RuntimeException(LICENSE_NUMBER_EXISTS + dto.getLicenseNumber());
        }


        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException(EMPLOYEE_NOT_FOUND + dto.getEmployeeId()));

        if (medicalStaffRepository.findByEmployeeId(employee.getId()).isPresent()) {
            throw new RuntimeException("Employee with ID " + dto.getEmployeeId() + " is already registered as Medical Staff.");
        }

        MedicalStaff medicalStaff = medicalStaffMapper.toEntity(dto);
        medicalStaff.setEmployee(employee);

        MedicalStaff savedMedicalStaff = medicalStaffRepository.save(medicalStaff);
        return medicalStaffMapper.toDetailsDto(savedMedicalStaff);
    }

    @Transactional
    public MedicalStaffDetailsDto update(Long id, MedicalStaffRequestDto dto) {
        MedicalStaff medicalStaff = medicalStaffRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(MEDICAL_STAFF_NOT_FOUND + id));


        if (medicalStaffRepository.existsByLicenseNumberAndIdNot(dto.getLicenseNumber(), id)) {
            throw new RuntimeException(LICENSE_NUMBER_EXISTS + dto.getLicenseNumber());
        }


        if (!medicalStaff.getEmployee().getId().equals(dto.getEmployeeId())) {

            Employee newEmployee = employeeRepository.findById(dto.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException(EMPLOYEE_NOT_FOUND + dto.getEmployeeId()));


            Optional<MedicalStaff> existingStaffByNewEmployee = medicalStaffRepository.findByEmployeeId(newEmployee.getId());
            if (existingStaffByNewEmployee.isPresent() && !existingStaffByNewEmployee.get().getId().equals(id)) {
                throw new RuntimeException("Employee with ID " + dto.getEmployeeId() + " is already registered as Medical Staff.");
            }
            medicalStaff.setEmployee(newEmployee);
        }

        MedicalStaff updatedMedicalStaff = medicalStaffMapper.updateEntityFromDto(medicalStaff, dto);
        MedicalStaff savedMedicalStaff = medicalStaffRepository.save(updatedMedicalStaff);

        return medicalStaffMapper.toDetailsDto(savedMedicalStaff);
    }

    @Transactional
    public void delete(Long id) {
        if (!medicalStaffRepository.existsById(id)) {
            throw new RuntimeException(MEDICAL_STAFF_NOT_FOUND + id);
        }
        medicalStaffRepository.deleteById(id);
    }



}
