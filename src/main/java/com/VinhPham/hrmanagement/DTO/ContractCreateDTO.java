package com.VinhPham.hrmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractCreateDTO {
    private Long id;
    private String startDate;
    private String endDate;
    private BigDecimal basicSalary;
}
