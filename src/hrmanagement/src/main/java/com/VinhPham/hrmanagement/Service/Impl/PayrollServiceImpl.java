package com.VinhPham.hrmanagement.Service.Impl;

import com.VinhPham.hrmanagement.Converter.PayrollDTOConverter;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.PayrollDTO;
import com.VinhPham.hrmanagement.ENUM.AttendanceStatusEnum;
import com.VinhPham.hrmanagement.ENUM.RewardTypeEnum;
import com.VinhPham.hrmanagement.Entity.*;
import com.VinhPham.hrmanagement.Repository.*;
import com.VinhPham.hrmanagement.Service.PayrollService;
import com.VinhPham.hrmanagement.Utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class PayrollServiceImpl implements PayrollService {
    @Autowired
    PayrollRepository payrollRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    TimesheetManagementRepository timesheetManagementRepository;

    @Autowired
    RewardDisciplineRepository rewardDisciplineRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PayrollDTOConverter payrollDTOConverter;

    @Autowired
    SecurityUtils securityUtils;

    @Override
    public List<PayrollDTO> viewAllPayroll(LocalDate month) {
        return payrollDTOConverter.convertToPayrollDTOList(payrollRepository.findAllActivePayroll(month));
    }

    @Override
    public PayrollDTO emplViewPayroll(LocalDate month) {
        EmployeeEntity employee = securityUtils.getCurrentEmployee();

        PayrollEntity payroll = payrollRepository.findByEmployeeIdAndMonth(employee.getId(), month);
        return payrollDTOConverter.convertToPayrollDTO(payroll);
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> generatePayroll(LocalDate month) {
        LocalDate startOfMonth = month.withDayOfMonth(1);
        LocalDate endOfMonth = month.withDayOfMonth(month.lengthOfMonth());

        List<EmployeeEntity> employeeList = employeeRepository.findAll();

        int standardDays = 26;

        for (EmployeeEntity employee : employeeList) {
            if (payrollRepository.existsByEmployeeAndMonth(employee, month)) {
                continue;
            }

            ContractEntity contract = contractRepository
                    .findActiveContract(employee.getId(), startOfMonth, endOfMonth)
                    .orElse(null);

            if(contract == null) {
                System.out.println("No Contract " + employee.getId());
                continue;
            }

            BigDecimal baseSalary = contract.getBasicSalary();

            Double coefficient = employee.getPosition().getSalaryCoefficient();

            BigDecimal actualBase = baseSalary.multiply(BigDecimal.valueOf(coefficient));

            List<TimesheetManagementEntity> timesheets =
                    timesheetManagementRepository.findByEmployeeAndMonth(employee.getId(), startOfMonth, endOfMonth);

            long workingDays = timesheets.stream()
                    .filter(t -> t.getAttendanceStatus().equals(AttendanceStatusEnum.DUNG_GIO))
                    .count();

            BigDecimal salaryByDays = actualBase
                    .divide(BigDecimal.valueOf(standardDays), 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(workingDays));

            List<RewardDisciplineEntity> rewards =
                    rewardDisciplineRepository.findByEmployeeAndMonth(employee.getId(), startOfMonth, endOfMonth);

            BigDecimal bonus = BigDecimal.ZERO;
            BigDecimal penalty = BigDecimal.ZERO;

            for (RewardDisciplineEntity r : rewards) {
                if (r.getType() == RewardTypeEnum.KHEN_THUONG) {
                    bonus = bonus.add(r.getAmount());
                } else {
                    penalty = penalty.add(r.getAmount());
                }
            }

            BigDecimal finalSalary = salaryByDays
                    .add(bonus)
                    .subtract(penalty);

            PayrollEntity payroll = new PayrollEntity();
            payroll.setEmployee(employee);
            payroll.setMonth(month);
            payroll.setSalary(finalSalary);
            payroll.setMonthlyBonus(bonus);

            payrollRepository.save(payroll);
        }

        return ResponseEntity.ok(
                ApiResponseDTO.builder()
                        .status("SUCCESS")
                        .message("Generate payroll successfully!")
                        .build()
        );
    }
}
