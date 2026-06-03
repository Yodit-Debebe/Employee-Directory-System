package com.act.intern.employeedirectory.application.port.output;

import com.act.intern.employeedirectory.domain.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

public interface EmployeeRepositoryPort {

    Employee save(Employee employee);

    Optional<Employee> findById(Long id);

    Page<Employee> findAll(Pageable pageable);

    boolean existsByEmail(String email);

    void delete(Employee employee);

    Page<Employee> findByDepartmentId(
            Long departmentId,
            Pageable pageable
    );

    Page<Employee> findBySalaryBetween(
            BigDecimal minSalary,
            BigDecimal maxSalary,
            Pageable pageable
    );

    Page<Employee>
    findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName,
            Pageable pageable
    );
}