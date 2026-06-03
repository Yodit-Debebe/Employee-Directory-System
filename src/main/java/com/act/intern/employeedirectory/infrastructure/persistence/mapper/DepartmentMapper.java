package com.act.intern.employeedirectory.infrastructure.persistence.mapper;

import com.act.intern.employeedirectory.domain.model.Department;
import com.act.intern.employeedirectory.infrastructure.persistence.entity.DepartmentEntity;

public class DepartmentMapper {

    public static Department toDomain(DepartmentEntity entity) {

        if (entity == null) {
            return null;
        }

        return Department.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public static DepartmentEntity toEntity(Department department) {

        if (department == null) {
            return null;
        }

        return DepartmentEntity.builder()
                .id(department.getId())
                .name(department.getName())
                .description(department.getDescription())
                .build();
    }
}