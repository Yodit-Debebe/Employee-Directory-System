package com.act.intern.employeedirectory.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class Employee {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private BigDecimal salary;

    private LocalDate hireDate;

    private Department department;
}
