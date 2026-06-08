package com.act.intern.employeedirectory.adapter.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeResponse(

        Long id,
        String firstName,
        String lastName,
        String email,
        BigDecimal salary,
        LocalDate hireDate,
        String departmentName
) {
}
