package com.act.intern.employeedirectory.infrastructure.persistence.repository;

import com.act.intern.employeedirectory.infrastructure.persistence.entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface EmployeeJpaRepository
        extends JpaRepository<EmployeeEntity, Long> {

    Page<EmployeeEntity> findByDepartmentId(
            Long departmentId,
            Pageable pageable
    );

    boolean existsByEmail(String email);

    Page<EmployeeEntity>
    findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName,
            Pageable pageable
    );

    Page<EmployeeEntity> findBySalaryBetween(
            BigDecimal minSalary,
            BigDecimal maxSalary,
            Pageable pageable
    );
}