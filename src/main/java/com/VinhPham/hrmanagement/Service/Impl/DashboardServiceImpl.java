package com.VinhPham.hrmanagement.Service.Impl;

import com.VinhPham.hrmanagement.DTO.DashboardDTO;
import com.VinhPham.hrmanagement.DTO.DepartmentStatDTO;
import com.VinhPham.hrmanagement.DTO.RankingDTO;
import com.VinhPham.hrmanagement.DTO.RewardDisciplineDTO;
import com.VinhPham.hrmanagement.ENUM.RewardTypeEnum;
import com.VinhPham.hrmanagement.Repository.DepartmentRepository;
import com.VinhPham.hrmanagement.Repository.EmployeeRepository;
import com.VinhPham.hrmanagement.Repository.RewardDisciplineRepository;
import com.VinhPham.hrmanagement.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RewardDisciplineRepository rewardDisciplineRepository;

    public DashboardDTO getDashboard() {

        Long totalEmployees = employeeRepository.count();

        Long totalDepartments = departmentRepository.count();

        Double avgSalary = employeeRepository.getAverageSalary();

        List<DepartmentStatDTO> departmentStats =
                employeeRepository.getDepartmentStat();

        List<RankingDTO> topReward = rewardDisciplineRepository.getTopEmployeeByType(RewardTypeEnum.KHEN_THUONG, PageRequest.of(0, 10));

        List<RankingDTO> topDiscipline = rewardDisciplineRepository.getTopEmployeeByType(RewardTypeEnum.KY_LUAT, PageRequest.of(0, 10));

        BigDecimal totalReward = rewardDisciplineRepository.sumAmountByType(RewardTypeEnum.KHEN_THUONG);

        BigDecimal totalDiscipline = rewardDisciplineRepository.sumAmountByType(RewardTypeEnum.KHEN_THUONG);

        RewardDisciplineDTO rewardDisciplineDTO = RewardDisciplineDTO.builder()
                .totalReward(totalReward)
                .totalDiscipline(totalDiscipline)
                .build();


        return DashboardDTO.builder()
                .totalEmployees(totalEmployees)
                .totalDepartments(totalDepartments)
                .avgSalary(avgSalary != null ? avgSalary : 0)
                .departmentStats(departmentStats)
                .rewardRanking(topReward)
                .disciplineRanking(topDiscipline)
                .rewardDisciplineDTO(rewardDisciplineDTO)
                .build();
    }
}
