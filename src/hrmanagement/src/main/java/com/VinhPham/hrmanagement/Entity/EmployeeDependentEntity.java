package com.VinhPham.hrmanagement.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee_dependents")
public class EmployeeDependentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "relationship", length = 100)
    private String relationship;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "deduction_start_date")
    private LocalDate deductionStartDate;

    @Column(name = "deduction_end_date")
    private LocalDate deductionEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
}

