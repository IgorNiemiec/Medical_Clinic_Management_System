package org.example.medical_clinic_management_system.controller.person;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.dto.person.*;
import org.example.medical_clinic_management_system.model.person.Employee;
import org.example.medical_clinic_management_system.service.person.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController
{

    private final EmployeeService employeeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeDetailsDto> createEmployee(@Valid @RequestBody EmployeeRequestDto requestDto) {
        EmployeeDetailsDto newEmployee = employeeService.createEmployee(requestDto);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EmployeeListItemDto>> getAllEmployees() {
        List<EmployeeListItemDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }


    @GetMapping("/{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeDetailsDto> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeDetailsDto employee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeDetailsDto> updateEmployee(@PathVariable Long employeeId,
                                                             @Valid @RequestBody EmployeeRequestDto requestDto) {
        EmployeeDetailsDto updatedEmployee = employeeService.updateEmployee(employeeId, requestDto);
        return ResponseEntity.ok(updatedEmployee);
    }


    @DeleteMapping("/{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

}
