package com.act.intern.employeedirectory.adapter.web.controller;

import com.act.intern.employeedirectory.application.command.CreateEmployeeCommand;
import com.act.intern.employeedirectory.application.command.UpdateEmployeeCommand;
import com.act.intern.employeedirectory.application.port.input.EmployeeUseCase;
import com.act.intern.employeedirectory.domain.model.Employee;
import com.act.intern.employeedirectory.adapter.web.dto.EmployeeRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeUseCase employeeUseCase;

    public EmployeeController(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        CreateEmployeeCommand command =
                new CreateEmployeeCommand(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getSalary(),
                        request.getHireDate(),
                        request.getDepartmentId()
                );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeUseCase.createEmployee(command));
    };

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        UpdateEmployeeCommand command =
                new UpdateEmployeeCommand(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getSalary(),
                        request.getHireDate(),
                        request.getDepartmentId()
                );

        return ResponseEntity.ok(
                employeeUseCase.updateEmployee(id, command)
        );
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployees(

            @RequestParam(required = false) Long departmentId,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size
    ) {

        if (departmentId != null) {

            return ResponseEntity.ok(
                    employeeUseCase.getEmployeesByDepartment(
                            departmentId,
                            page,
                            size
                    )
            );
        }

        return ResponseEntity.ok(
                employeeUseCase.getAllEmployees(page, size)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

        return ResponseEntity.ok(
                employeeUseCase.getEmployeeById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

        employeeUseCase.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Employee>> searchEmployees(

            @RequestParam String keyword,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size
    ) {

        return ResponseEntity.ok(
                employeeUseCase.searchEmployees(
                        keyword,
                        page,
                        size
                )
        );
    }

    @GetMapping("/filter/salary")
    public ResponseEntity<Page<Employee>> filterEmployeesBySalary(

            @RequestParam BigDecimal minSalary,

            @RequestParam BigDecimal maxSalary,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size
    ) {

        return ResponseEntity.ok(

                employeeUseCase.filterEmployeesBySalary(
                        minSalary,
                        maxSalary,
                        page,
                        size
                )
        );
    }
}