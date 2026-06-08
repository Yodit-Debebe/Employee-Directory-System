package com.act.intern.employeedirectory.adapter.web.mapper;

import com.act.intern.employeedirectory.adapter.web.dto.EmployeeResponse;
import com.act.intern.employeedirectory.domain.model.Employee;

public class EmployeeResponseMapper {

    public static EmployeeResponse toResponse(
            Employee employee) {

        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getSalary(),
                employee.getHireDate(),

                employee.getDepartment() != null
                        ? employee.getDepartment().getName()
                        : null
        );
    }
}
