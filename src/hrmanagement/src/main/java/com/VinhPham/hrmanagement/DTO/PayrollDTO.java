package com.VinhPham.hrmanagement.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PayrollDTO {
    private Long id;
    private LocalDate month;
    private BigDecimal salary;
    private BigDecimal monthlyBonus;
    private String EmployeeName;
}
