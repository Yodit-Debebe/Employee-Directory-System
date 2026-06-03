package com.act.intern.employeedirectory.application.port.output;

import com.act.intern.employeedirectory.domain.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepositoryPort {

    Department save(Department department);

    Optional<Department> findById(Long id);

    List<Department> findAll();

    boolean existsByName(String name);

    void delete(Department department);
}