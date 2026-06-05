package com.VinhPham.hrmanagement.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardDTO {
    private Long totalEmployees;
    private Long totalDepartments;
    private Double avgSalary;
    private List<DepartmentStatDTO> departmentStats;
    private RewardDisciplineDTO rewardDisciplineDTO;
    private List<RankingDTO> rewardRanking;
    private List<RankingDTO> disciplineRanking;
}
