package com.act.intern.employeedirectory.adapter.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeRequest {

    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @Positive(message = "salary must be positive")
    @NotNull
    private BigDecimal salary;

    @NotNull(message = "hiredate must not be null")
    private LocalDate hireDate;

    @NotNull(message = "department required")
    private Long departmentId;

}
