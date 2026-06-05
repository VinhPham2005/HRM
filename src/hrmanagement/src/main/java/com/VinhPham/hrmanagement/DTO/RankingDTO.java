package com.VinhPham.hrmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankingDTO {
    private Long employeeId;
    private String employeeName;
    private String departmentName;
    private BigDecimal totalAmount;
}
