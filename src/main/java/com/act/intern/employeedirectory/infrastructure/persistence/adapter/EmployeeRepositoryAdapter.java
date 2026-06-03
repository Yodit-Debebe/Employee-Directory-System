package com.act.intern.employeedirectory.infrastructure.persistence.adapter;

import com.act.intern.employeedirectory.application.port.output.EmployeeRepositoryPort;
import com.act.intern.employeedirectory.domain.model.Employee;
import com.act.intern.employeedirectory.infrastructure.persistence.mapper.EmployeeMapper;
import com.act.intern.employeedirectory.infrastructure.persistence.repository.EmployeeJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class EmployeeRepositoryAdapter implements EmployeeRepositoryPort {

    private final EmployeeJpaRepository repository;

    public EmployeeRepositoryAdapter(EmployeeJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee save(Employee employee) {

        return EmployeeMapper.toDomain(
                repository.save(
                        EmployeeMapper.toEntity(employee)
                )
        );
    }

    @Override
    public Optional<Employee> findById(Long id) {

        return repository.findById(id)
                .map(EmployeeMapper::toDomain);
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {

        return repository.findAll(pageable)
                .map(EmployeeMapper::toDomain);
    }

    @Override
    public Page<Employee> findByDepartmentId(
            Long departmentId,
            Pageable pageable) {

        return repository.findByDepartmentId(
                        departmentId,
                        pageable
                )
                .map(EmployeeMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {

        return repository.existsByEmail(email);
    }

    @Override
    public Page<Employee>
    findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName,
            Pageable pageable) {

        return repository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        firstName,
                        lastName,
                        pageable
                )
                .map(EmployeeMapper::toDomain);
    }

    @Override
    public Page<Employee> findBySalaryBetween(
            BigDecimal minSalary,
            BigDecimal maxSalary,
            Pageable pageable) {

        return repository.findBySalaryBetween(
                        minSalary,
                        maxSalary,
                        pageable
                )
                .map(EmployeeMapper::toDomain);
    }

    @Override
    public void delete(Employee employee) {

        repository.delete(
                EmployeeMapper.toEntity(employee)
        );
    }
}