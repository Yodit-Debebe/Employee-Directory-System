package com.act.intern.employeedirectory.controller;

import com.act.intern.employeedirectory.domain.Employee;
import com.act.intern.employeedirectory.dto.EmployeeRequest;
import com.act.intern.employeedirectory.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        return new ResponseEntity<>(
                employeeService.createEmployee(request),
                HttpStatus.CREATED
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
                    employeeService.getEmployeesByDepartment(
                            departmentId,
                            page,
                            size
                    )
            );
        }

        return ResponseEntity.ok(
                employeeService.getAllEmployees(page, size)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Employee>> searchEmployees(

            @RequestParam String keyword,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "5") int size
    ) {

        return ResponseEntity.ok(
                employeeService.searchEmployees(
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

                employeeService.filterEmployeesBySalary(
                        minSalary,
                        maxSalary,
                        page,
                        size
                )
        );
    }
}