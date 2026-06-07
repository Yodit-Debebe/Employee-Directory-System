package com.act.intern.employeedirectory.application.port.input;

import com.act.intern.employeedirectory.application.command.CreateEmployeeCommand;
import com.act.intern.employeedirectory.application.command.UpdateEmployeeCommand;
import com.act.intern.employeedirectory.domain.model.Employee;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface EmployeeUseCase {

    Employee createEmployee(
            CreateEmployeeCommand command
    );

    Employee updateEmployee(
            Long id,
            UpdateEmployeeCommand command
    );

    Employee getEmployeeById(Long id);

    Page<Employee> getAllEmployees(int page, int size);

    Page<Employee> getEmployeesByDepartment(
            Long departmentId,
            int page,
            int size
    );

    Page<Employee> searchEmployees(
            String keyword,
            int page,
            int size
    );

    Page<Employee> filterEmployeesBySalary(
            BigDecimal minSalary,
            BigDecimal maxSalary,
            int page,
            int size
    );

    void deleteEmployee(Long id);
}