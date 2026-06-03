package com.act.intern.employeedirectory.application.service;

import com.act.intern.employeedirectory.adapter.web.dto.DepartmentRequest;
import com.act.intern.employeedirectory.application.port.input.DepartmentUseCase;
import com.act.intern.employeedirectory.application.port.output.DepartmentRepositoryPort;
import com.act.intern.employeedirectory.domain.exception.DuplicateResourceException;
import com.act.intern.employeedirectory.domain.exception.ResourceNotFoundException;
import com.act.intern.employeedirectory.domain.model.Department;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DepartmentApplicationService
        implements DepartmentUseCase {

    private final DepartmentRepositoryPort departmentRepositoryPort;

    public DepartmentApplicationService(
            DepartmentRepositoryPort departmentRepositoryPort) {

        this.departmentRepositoryPort = departmentRepositoryPort;
    }

    @Override
    public List<Department> getAllDepartments() {

        return departmentRepositoryPort.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {

        return departmentRepositoryPort.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Department not found"
                        )
                );
    }

    @Override
    public Department createDepartment(
            DepartmentRequest request) {

        if (departmentRepositoryPort.existsByName(
                request.getName())) {

            throw new DuplicateResourceException(
                    "Department name already exists"
            );
        }

        Department department = Department.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return departmentRepositoryPort.save(department);
    }

    @Override
    public Department updateDepartment(
            Long id,
            DepartmentRequest request) {

        Department department =
                getDepartmentById(id);

        department = Department.builder()
                .id(department.getId())
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return departmentRepositoryPort.save(department);
    }

    @Override
    public void deleteDepartment(Long id) {

        Department department =
                getDepartmentById(id);

        departmentRepositoryPort.delete(department);
    }
}