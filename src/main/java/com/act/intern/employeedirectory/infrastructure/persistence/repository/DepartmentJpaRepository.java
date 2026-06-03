package com.act.intern.employeedirectory.infrastructure.persistence.repository;

import com.act.intern.employeedirectory.infrastructure.persistence.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentJpaRepository
        extends JpaRepository<DepartmentEntity, Long> {

    boolean existsByName(String name);
}