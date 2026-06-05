package com.VinhPham.hrmanagement.Service.Impl;

import com.VinhPham.hrmanagement.Converter.InsuranceDTOConverter;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.InsuranceDTO;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import com.VinhPham.hrmanagement.Entity.EmployeeInsuranceEntity;
import com.VinhPham.hrmanagement.Entity.InsuranceEntity;
import com.VinhPham.hrmanagement.Repository.EmployeeInsuranceRepository;
import com.VinhPham.hrmanagement.Repository.InsuranceRepository;
import com.VinhPham.hrmanagement.Service.EmployeeInsuranceService;
import com.VinhPham.hrmanagement.Utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeInsuranceServiceImpl implements EmployeeInsuranceService {
    @Autowired
    private EmployeeInsuranceRepository employeeInsuranceRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private InsuranceDTOConverter insuranceDTOConverter;

    @Override
    public List<InsuranceDTO> getAllEmployeeInsurances() {
        EmployeeEntity employee = securityUtils.getCurrentEmployee();
        List<EmployeeInsuranceEntity> employeeInsurances = employeeInsuranceRepository.findByEmployeeId(employee.getId());
        
        List<InsuranceEntity> insuranceEntities = new ArrayList<>();
        for (EmployeeInsuranceEntity employeeInsurance : employeeInsurances) {
            insuranceEntities.add(employeeInsurance.getInsurance());
        }
        
        return insuranceDTOConverter.convertToInsuranceDTOList(insuranceEntities);
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> createEmployeeInsurance(InsuranceDTO insuranceDTO) {
        try {
            EmployeeEntity employee = securityUtils.getCurrentEmployee();
            
            InsuranceEntity insuranceEntity = null;
            if (insuranceDTO.getId() != null) {
                insuranceEntity = insuranceRepository.findById(insuranceDTO.getId()).orElse(null);
            } else if (insuranceDTO.getName() != null) {
                insuranceEntity = insuranceRepository.findByName(insuranceDTO.getName());
            }

            if (insuranceEntity == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ApiResponseDTO.builder()
                                .status("ERROR")
                                .message("Insurance not found")
                                .build()
                );
            }

            EmployeeInsuranceEntity existing = employeeInsuranceRepository.findByEmployeeIdAndInsuranceId(employee.getId(), insuranceEntity.getId());
            if (existing != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        ApiResponseDTO.builder()
                                .status("ERROR")
                                .message("Employee already has this insurance")
                                .build()
                );
            }

            EmployeeInsuranceEntity employeeInsuranceEntity = EmployeeInsuranceEntity.builder()
                    .employee(employee)
                    .insurance(insuranceEntity)
                    .build();

            employeeInsuranceRepository.save(employeeInsuranceEntity);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Insurance assigned to employee successfully")
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to assign insurance: " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> editEmployeeInsurance(InsuranceDTO insuranceDTO) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                ApiResponseDTO.builder()
                        .status("ERROR")
                        .message("Editing insurance association is not supported. Please remove and re-add.")
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> deleteEmployeeInsurance(InsuranceDTO insuranceDTO) {
        try {
            EmployeeEntity employee = securityUtils.getCurrentEmployee();
            
            InsuranceEntity insuranceEntity = null;
            if (insuranceDTO.getId() != null) {
                insuranceEntity = insuranceRepository.findById(insuranceDTO.getId()).orElse(null);
            } else if (insuranceDTO.getName() != null) {
                insuranceEntity = insuranceRepository.findByName(insuranceDTO.getName());
            }

            if (insuranceEntity == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ApiResponseDTO.builder()
                                .status("ERROR")
                                .message("Insurance not found")
                                .build()
                );
            }

            EmployeeInsuranceEntity employeeInsurance = employeeInsuranceRepository.findByEmployeeIdAndInsuranceId(employee.getId(), insuranceEntity.getId());
            if (employeeInsurance == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ApiResponseDTO.builder()
                                .status("ERROR")
                                .message("Employee does not have this insurance")
                                .build()
                );
            }

            employeeInsuranceRepository.delete(employeeInsurance);
            
            return ResponseEntity.ok(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Insurance removed from employee successfully")
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to remove insurance: " + e.getMessage())
                            .build()
            );
        }
    }
}
