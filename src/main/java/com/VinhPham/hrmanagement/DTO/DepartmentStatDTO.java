package com.VinhPham.hrmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentStatDTO {
    private String departmentName;
    private Long employeeCount;
    private Double departmentAvgSalary;
}
