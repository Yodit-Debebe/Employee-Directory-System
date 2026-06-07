package com.act.intern.employeedirectory.application.command;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateEmployeeCommand(
        String firstName,
        String lastName,
        String email,
        BigDecimal salary,
        LocalDate hireDate,
        Long departmentId
) {
}
