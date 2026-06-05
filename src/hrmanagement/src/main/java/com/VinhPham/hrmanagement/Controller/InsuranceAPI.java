package com.VinhPham.hrmanagement.Controller;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.InsuranceDTO;
import com.VinhPham.hrmanagement.Service.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/insurance")
public class InsuranceAPI {
    @Autowired
    InsuranceService insuranceService;

    @GetMapping
    public List<InsuranceDTO> getAllInsurances() {
        return insuranceService.getAllInsurances();
    }

    @GetMapping("/id/{id}")
    public InsuranceDTO getInsuranceById(@PathVariable Long id) {
        return insuranceService.getInsuranceById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<?>> createInsurance(@Valid @RequestBody InsuranceDTO insuranceDTO) {
        return insuranceService.createInsurance(insuranceDTO);
    }

    @PatchMapping("/edit")
    public ResponseEntity<ApiResponseDTO<?>> editInsurance(@RequestBody InsuranceDTO insuranceDTO) {
        return insuranceService.editInsurance(insuranceDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<?>> deleteInsurance(@PathVariable Long id) {
        return insuranceService.deleteInsurance(id);
    }
}
