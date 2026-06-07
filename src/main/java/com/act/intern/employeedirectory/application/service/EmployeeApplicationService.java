package com.act.intern.employeedirectory.application.service;

import com.act.intern.employeedirectory.application.command.CreateEmployeeCommand;
import com.act.intern.employeedirectory.application.command.UpdateEmployeeCommand;
import com.act.intern.employeedirectory.application.port.input.EmployeeUseCase;
import com.act.intern.employeedirectory.application.port.output.DepartmentRepositoryPort;
import com.act.intern.employeedirectory.application.port.output.EmployeeRepositoryPort;
import com.act.intern.employeedirectory.domain.exception.DuplicateResourceException;
import com.act.intern.employeedirectory.domain.exception.ResourceNotFoundException;
import com.act.intern.employeedirectory.domain.model.Department;
import com.act.intern.employeedirectory.domain.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class EmployeeApplicationService
        implements EmployeeUseCase {

    private final EmployeeRepositoryPort employeeRepositoryPort;
    private final DepartmentRepositoryPort departmentRepositoryPort;

    public EmployeeApplicationService(
            EmployeeRepositoryPort employeeRepositoryPort,
            DepartmentRepositoryPort departmentRepositoryPort) {

        this.employeeRepositoryPort = employeeRepositoryPort;
        this.departmentRepositoryPort = departmentRepositoryPort;
    }

    @Override
    public Employee createEmployee(CreateEmployeeCommand command) {

        if (employeeRepositoryPort.existsByEmail(command.email())) {
            throw new DuplicateResourceException(
                    "Email already exists"
            );
        }

        Department department =
                departmentRepositoryPort.findById(
                        command.departmentId()
                ).orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Department not found"
                        )
                );

        Employee employee = Employee.builder()
                .firstName(command.firstName())
                .lastName(command.lastName())
                .email(command.email())
                .salary(command.salary())
                .hireDate(command.hireDate())
                .department(department)
                .build();

        return employeeRepositoryPort.save(employee);
    }

    @Override
    public Employee updateEmployee(
            Long id,
            UpdateEmployeeCommand command) {

        Employee employee =
                getEmployeeById(id);

        Department department =
                departmentRepositoryPort.findById(
                        command.departmentId()
                ).orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Department not found"
                        )
                );

        employee = Employee.builder()
                .id(employee.getId())
                .firstName(command.firstName())
                .lastName(command.lastName())
                .email(command.email())
                .salary(command.salary())
                .hireDate(command.hireDate())
                .department(department)
                .build();

        return employeeRepositoryPort.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {

        return employeeRepositoryPort.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Employee not found"
                        )
                );
    }

    @Override
    public Page<Employee> getAllEmployees(
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return employeeRepositoryPort.findAll(pageable);
    }

    @Override
    public Page<Employee> getEmployeesByDepartment(
            Long departmentId,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return employeeRepositoryPort.findByDepartmentId(
                departmentId,
                pageable
        );
    }

    @Override
    public Page<Employee> searchEmployees(
            String keyword,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return employeeRepositoryPort
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        keyword,
                        keyword,
                        pageable
                );
    }

    @Override
    public Page<Employee> filterEmployeesBySalary(
            BigDecimal minSalary,
            BigDecimal maxSalary,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return employeeRepositoryPort.findBySalaryBetween(
                minSalary,
                maxSalary,
                pageable
        );
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee =
                getEmployeeById(id);

        employeeRepositoryPort.delete(employee);
    }
}