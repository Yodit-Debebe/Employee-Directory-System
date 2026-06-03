package com.act.intern.employeedirectory.infrastructure.persistence.mapper;

import com.act.intern.employeedirectory.domain.model.Employee;
import com.act.intern.employeedirectory.infrastructure.persistence.entity.EmployeeEntity;

public class EmployeeMapper {

    public static Employee toDomain(EmployeeEntity entity) {

        if (entity == null) {
            return null;
        }

        return Employee.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .salary(entity.getSalary())
                .hireDate(entity.getHireDate())
                .department(
                        DepartmentMapper.toDomain(
                                entity.getDepartment()
                        )
                )
                .build();
    }

    public static EmployeeEntity toEntity(Employee employee) {

        if (employee == null) {
            return null;
        }

        return EmployeeEntity.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .hireDate(employee.getHireDate())
                .department(
                        DepartmentMapper.toEntity(
                                employee.getDepartment()
                        )
                )
                .build();
    }
}