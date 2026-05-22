package com.act.intern.employeedirectory.service;

import com.act.intern.employeedirectory.domain.Department;
import com.act.intern.employeedirectory.domain.Employee;
import com.act.intern.employeedirectory.dto.EmployeeRequest;
import com.act.intern.employeedirectory.exception.DuplicateResourceException;
import com.act.intern.employeedirectory.exception.ResourceNotFoundException;
import com.act.intern.employeedirectory.repository.DepartmentRepository;
import com.act.intern.employeedirectory.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Page<Employee> getAllEmployees(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return employeeRepository.findAll(pageable);
    }


    public Page<Employee> getEmployeesByDepartment(
            Long departmentId,
            int page,
            int size) {

        Pageable pageable = PageRequest.of(page, size);

        return employeeRepository.findByDepartmentId(
                departmentId,
                pageable
        );
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
    }

    @Transactional
    public Employee createEmployee(EmployeeRequest request) {

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("Email already exists");
        }

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        Employee employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .salary(request.getSalary())
                .hireDate(request.getHireDate())
                .department(department)
                .build();

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Long id, EmployeeRequest request) {

        Employee employee = getEmployeeById(id);

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setSalary(request.getSalary());
        employee.setHireDate(request.getHireDate());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {

        Employee employee = getEmployeeById(id);

        employeeRepository.delete(employee);
    }


    public Page<Employee> searchEmployees(
            String keyword,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return employeeRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        keyword,
                        keyword,
                        pageable
                );
    }

    public Page<Employee> filterEmployeesBySalary(

            BigDecimal minSalary,
            BigDecimal maxSalary,

            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return employeeRepository.findBySalaryBetween(
                minSalary,
                maxSalary,
                pageable
        );
    }


}