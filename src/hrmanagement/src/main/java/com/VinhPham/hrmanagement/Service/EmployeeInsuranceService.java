package com.VinhPham.hrmanagement.Service;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.InsuranceDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeInsuranceService {
    List<InsuranceDTO> getAllEmployeeInsurances();
    ResponseEntity<ApiResponseDTO<?>> createEmployeeInsurance(InsuranceDTO insuranceDTO);
    ResponseEntity<ApiResponseDTO<?>> editEmployeeInsurance(InsuranceDTO insuranceDTO);
    ResponseEntity<ApiResponseDTO<?>> deleteEmployeeInsurance(InsuranceDTO insuranceDTO);
}
