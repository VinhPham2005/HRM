package com.VinhPham.hrmanagement.Controller;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.PayrollDTO;
import com.VinhPham.hrmanagement.Service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PayrollAPI {
    @Autowired
    PayrollService payrollService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/payroll")
    public List<PayrollDTO> viewAll(){
        LocalDate month = LocalDate.now();
        return payrollService.viewAllPayroll(month);
    }

    @GetMapping("/employee/payroll")
    public PayrollDTO emplViewPayroll() {
        LocalDate month = LocalDate.now();
        return payrollService.emplViewPayroll(month);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/payroll/generate")
    public ResponseEntity<ApiResponseDTO<?>> generatePayroll() {
        LocalDate month = LocalDate.now();
        return payrollService.generatePayroll(month);
    }
}
