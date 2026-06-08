package com.act.intern.employeedirectory.adapter.web.mapper;

import com.act.intern.employeedirectory.adapter.web.dto.DepartmentResponse;
import com.act.intern.employeedirectory.domain.model.Department;

public class DepartmentResponseMapper {

    public static DepartmentResponse toResponse(
            Department department) {

        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }
}
