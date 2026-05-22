package com.act.intern.employeedirectory.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;
}
