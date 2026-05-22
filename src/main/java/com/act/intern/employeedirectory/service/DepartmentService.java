package com.act.intern.employeedirectory.service;

import com.act.intern.employeedirectory.domain.Department;
import com.act.intern.employeedirectory.dto.DepartmentRequest;
import com.act.intern.employeedirectory.exception.DuplicateResourceException;
import com.act.intern.employeedirectory.exception.ResourceNotFoundException;
import com.act.intern.employeedirectory.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }

    @Transactional
    public Department createDepartment(DepartmentRequest request) {

        if (departmentRepository.existsByName(request.getName())) {
            throw new DuplicateResourceException("Department name already exists");
        }

        Department department = Department.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        return departmentRepository.save(department);
    }

    @Transactional
    public Department updateDepartment(Long id, DepartmentRequest request) {

        Department department = getDepartmentById(id);

        department.setName(request.getName());
        department.setDescription(request.getDescription());

        return departmentRepository.save(department);
    }

    @Transactional
    public void deleteDepartment(Long id) {

        Department department = getDepartmentById(id);

        departmentRepository.delete(department);
    }
}