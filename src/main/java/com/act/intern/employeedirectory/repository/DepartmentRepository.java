package com.act.intern.employeedirectory.repository;

import com.act.intern.employeedirectory.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByName(String name);
}
