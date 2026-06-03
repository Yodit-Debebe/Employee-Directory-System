package com.act.intern.employeedirectory.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Department {

    private Long id;

    private String name;

    private String description;
}