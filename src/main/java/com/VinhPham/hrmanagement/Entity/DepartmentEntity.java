package com.VinhPham.hrmanagement.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name="department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="founded_date")
    private LocalDate foundedDate;

    @OneToMany(mappedBy = "department")
    private List<EmployeeEntity> employees;
}
