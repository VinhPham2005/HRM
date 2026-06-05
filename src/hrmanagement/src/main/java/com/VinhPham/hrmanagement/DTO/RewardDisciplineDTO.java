package com.VinhPham.hrmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RewardDisciplineDTO {
    private BigDecimal totalReward;
    private BigDecimal totalDiscipline;
}
