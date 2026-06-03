package com.act.intern.employeedirectory.adapter.web.controller;

import com.act.intern.employeedirectory.application.port.input.DepartmentUseCase;
import com.act.intern.employeedirectory.domain.model.Department;
import com.act.intern.employeedirectory.adapter.web.dto.DepartmentRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentUseCase departmentUseCase;

    public DepartmentController(DepartmentUseCase departmentUseCase) {
        this.departmentUseCase = departmentUseCase;
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(
            @Valid @RequestBody DepartmentRequest request) {    // @RequestBody - Converts JSON body into Java object

        return new ResponseEntity<>(
                departmentUseCase.createDepartment(request),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {

        return ResponseEntity.ok(
                departmentUseCase.getAllDepartments()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {

        return ResponseEntity.ok(
                departmentUseCase.getDepartmentById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequest request) {

        return ResponseEntity.ok(
                departmentUseCase.updateDepartment(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {

        departmentUseCase.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }
}