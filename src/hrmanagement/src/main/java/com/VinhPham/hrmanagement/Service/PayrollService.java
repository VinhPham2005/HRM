package com.VinhPham.hrmanagement.Service;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.PayrollDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface PayrollService {
    List<PayrollDTO> viewAllPayroll(LocalDate month);
    PayrollDTO emplViewPayroll(LocalDate month);
    ResponseEntity<ApiResponseDTO<?>> generatePayroll(LocalDate month);
}
