package com.act.intern.employeedirectory.application.command;

public record CreateDepartmentCommand(
        String name,
        String description
){
}
