package com.act.intern.employeedirectory.application.command;

public record UpdateDepartmentCommand(
        String name,
        String description
) {
}
