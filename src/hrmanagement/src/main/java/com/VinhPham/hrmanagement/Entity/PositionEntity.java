package com.VinhPham.hrmanagement.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "position")
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="position_name")
    private String positionName;

    @Column(name="salary_coefficient")
    private Double salaryCoefficient;

    @OneToMany(mappedBy = "position")
    private List<EmployeeEntity> employees;
}
