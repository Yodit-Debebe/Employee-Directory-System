package com.act.intern.employeedirectory.application.port.input;

import com.act.intern.employeedirectory.adapter.web.dto.DepartmentRequest;
import com.act.intern.employeedirectory.domain.model.Department;

import java.util.List;

public interface DepartmentUseCase {

    Department createDepartment(DepartmentRequest request);

    Department updateDepartment(Long id, DepartmentRequest request);

    Department getDepartmentById(Long id);

    List<Department> getAllDepartments();

    void deleteDepartment(Long id);
}