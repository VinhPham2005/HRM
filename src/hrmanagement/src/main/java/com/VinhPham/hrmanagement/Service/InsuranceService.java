package com.VinhPham.hrmanagement.Service;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.InsuranceDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InsuranceService {
    List<InsuranceDTO> getAllInsurances();
    InsuranceDTO getInsuranceById(Long id);
    ResponseEntity<ApiResponseDTO<?>> createInsurance(InsuranceDTO insuranceDTO);
    ResponseEntity<ApiResponseDTO<?>> editInsurance(InsuranceDTO insuranceDTO);
    ResponseEntity<ApiResponseDTO<?>> deleteInsurance(Long id);
}
