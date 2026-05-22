package com.act.intern.employeedirectory.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal salary;

    @Column( nullable = false)
    private LocalDate hireDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Department department;

    @Column(updatable = false, insertable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}
