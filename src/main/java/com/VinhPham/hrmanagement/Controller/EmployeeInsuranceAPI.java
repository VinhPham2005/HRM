package com.VinhPham.hrmanagement.Controller;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.InsuranceDTO;
import com.VinhPham.hrmanagement.Service.EmployeeInsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal-info/insurance")
public class EmployeeInsuranceAPI {
    @Autowired
    private EmployeeInsuranceService employeeInsuranceService;

    @GetMapping
    public List<InsuranceDTO> getAllEmployeeInsurances() {
        return employeeInsuranceService.getAllEmployeeInsurances();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<?>> createEmployeeInsurance(@RequestBody InsuranceDTO insuranceDTO) {
        return employeeInsuranceService.createEmployeeInsurance(insuranceDTO);
    }

    @PatchMapping("/edit")
    public ResponseEntity<ApiResponseDTO<?>> editEmployeeInsurance(@RequestBody InsuranceDTO insuranceDTO) {
        return employeeInsuranceService.editEmployeeInsurance(insuranceDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponseDTO<?>> deleteEmployeeInsurance(@RequestBody InsuranceDTO insuranceDTO) {
        return employeeInsuranceService.deleteEmployeeInsurance(insuranceDTO);
    }
}
