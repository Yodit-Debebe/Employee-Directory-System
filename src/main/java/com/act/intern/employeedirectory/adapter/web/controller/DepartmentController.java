package com.act.intern.employeedirectory.adapter.web.controller;

import com.act.intern.employeedirectory.adapter.web.dto.DepartmentRequest;
import com.act.intern.employeedirectory.adapter.web.dto.DepartmentResponse;
import com.act.intern.employeedirectory.adapter.web.mapper.DepartmentResponseMapper;
import com.act.intern.employeedirectory.application.command.CreateDepartmentCommand;
import com.act.intern.employeedirectory.application.command.UpdateDepartmentCommand;
import com.act.intern.employeedirectory.application.port.input.DepartmentUseCase;
import com.act.intern.employeedirectory.domain.model.Department;
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
    public ResponseEntity<DepartmentResponse> createDepartment(
            @Valid @RequestBody DepartmentRequest request) {

        CreateDepartmentCommand command =
                new CreateDepartmentCommand(
                        request.getName(),
                        request.getDescription()
                );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        DepartmentResponseMapper.toResponse(
                                departmentUseCase.createDepartment(
                                        command
                                )
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequest request) {

        UpdateDepartmentCommand command =
                new UpdateDepartmentCommand(
                        request.getName(),
                        request.getDescription()
                );

        return ResponseEntity.ok(
                DepartmentResponseMapper.toResponse(
                departmentUseCase.updateDepartment(id, command)
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {

        return ResponseEntity.ok(
                departmentUseCase
                        .getAllDepartments()
                        .stream()
                        .map(DepartmentResponseMapper::toResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {

        return ResponseEntity.ok(
                DepartmentResponseMapper.toResponse(
                departmentUseCase.getDepartmentById(id)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {

        departmentUseCase.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }
}