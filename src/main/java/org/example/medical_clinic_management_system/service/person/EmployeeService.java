package org.example.medical_clinic_management_system.service.person;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.*;
import org.example.medical_clinic_management_system.mapper.person.EmployeeMapper;
import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.model.person.MedicalStaff;
import org.example.medical_clinic_management_system.model.person.User;
import org.example.medical_clinic_management_system.repository.person.EmployeeRepository;
import org.example.medical_clinic_management_system.repository.person.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService
{

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;
    private final EmployeeMapper employeeMapper;

    @Transactional
    public EmployeeDetailsDto createEmployee(EmployeeRequestDto requestDto) {


        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + requestDto.getUserId()));


        if (employeeRepository.existsByServicePhone(requestDto.getServicePhone())) {
            throw new RuntimeException("Numer służbowy: " + requestDto.getServicePhone() + " jest już zajęty.");
        }


        Employee employee = employeeMapper.toEntity(requestDto);


        employee.setUser(user);


        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toDetailsDto(savedEmployee);
    }


    @Transactional
    public EmployeeDetailsDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));

        return employeeMapper.toDetailsDto(employee);
    }

    @Transactional
    public List<EmployeeListItemDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employeeMapper.toListItemDtoList(employees);
    }

    @Transactional
    public EmployeeDetailsDto updateEmployee(Long employeeId, EmployeeRequestDto requestDto) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + employeeId));


        if (!employee.getServicePhone().equals(requestDto.getServicePhone()) &&
                employeeRepository.existsByServicePhone(requestDto.getServicePhone())) {
            throw new RuntimeException("Numer służbowy: " + requestDto.getServicePhone() + " jest już zajęty przez innego pracownika.");
        }

        employee.setHireDate(requestDto.getHireDate());
        employee.setServicePhone(requestDto.getServicePhone());

        Employee updatedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDetailsDto(updatedEmployee);
    }


    @Transactional
    public void deleteEmployee(Long employeeId) {
        if (!employeeRepository.existsById(employeeId))
        {
            throw new RuntimeException("Employee not found with ID: " + employeeId);
        }

         employeeRepository.deleteById(employeeId);
    }



}
