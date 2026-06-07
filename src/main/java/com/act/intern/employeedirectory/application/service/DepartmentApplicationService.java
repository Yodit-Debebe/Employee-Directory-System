package com.act.intern.employeedirectory.application.service;

import com.act.intern.employeedirectory.application.command.CreateDepartmentCommand;
import com.act.intern.employeedirectory.application.command.UpdateDepartmentCommand;
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
    public Department createDepartment(
            CreateDepartmentCommand command) {

        if (departmentRepositoryPort.existsByName(
                command.name())) {

            throw new DuplicateResourceException(
                    "Department name already exists"
            );
        }

        Department department = Department.builder()
                .name(command.name())
                .description(command.description())
                .build();

        return departmentRepositoryPort.save(department);
    }

    @Override
    public Department updateDepartment(
            Long id,
            UpdateDepartmentCommand command) {

        Department department =
                getDepartmentById(id);

        department = Department.builder()
                .id(department.getId())
                .name(command.name())
                .description(command.description())
                .build();

        return departmentRepositoryPort.save(department);
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
    public void deleteDepartment(Long id) {

        Department department =
                getDepartmentById(id);

        departmentRepositoryPort.delete(department);
    }
}