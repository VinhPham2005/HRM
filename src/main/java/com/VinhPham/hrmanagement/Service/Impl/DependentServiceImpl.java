package com.VinhPham.hrmanagement.Service.Impl;

import com.VinhPham.hrmanagement.Converter.DependentDTOConverter;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.DependentDTO;
import com.VinhPham.hrmanagement.Entity.EmployeeDependentEntity;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import com.VinhPham.hrmanagement.Repository.EmployeeDependentRepository;
import com.VinhPham.hrmanagement.Service.DependentService;
import com.VinhPham.hrmanagement.Utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependentServiceImpl implements DependentService {
    @Autowired
    private EmployeeDependentRepository employeeDependentRepository;

    @Autowired
    private DependentDTOConverter dependentDTOConverter;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    public List<DependentDTO> getAllDependents() {
        EmployeeEntity employee = securityUtils.getCurrentEmployee();
        return dependentDTOConverter.convertToDependentDTOList(employeeDependentRepository.findByEmployeeId(employee.getId()));
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> createDependent(DependentDTO dependentDTO) {
        try {
            EmployeeEntity employee = securityUtils.getCurrentEmployee();

            EmployeeDependentEntity dependentEntity = new EmployeeDependentEntity();
            dependentEntity.setName(dependentDTO.getName());
            dependentEntity.setRelationship(dependentDTO.getRelationship());
            dependentEntity.setBirthday(dependentDTO.getBirthday());
            dependentEntity.setEmployee(employee);

            employeeDependentRepository.save(dependentEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Dependent created successfully")
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to create dependent: " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> updateDependent(DependentDTO dependentDTO) {
        try {
            EmployeeEntity employee = securityUtils.getCurrentEmployee();

            EmployeeDependentEntity dependentEntity = employeeDependentRepository.findByIdAndEmployeeId(dependentDTO.getId(), employee.getId());
            if (dependentEntity == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ApiResponseDTO.builder()
                                .status("ERROR")
                                .message("Dependent not found")
                                .build()
                );
            }

            dependentEntity.setName(dependentDTO.getName());
            dependentEntity.setRelationship(dependentDTO.getRelationship());
            dependentEntity.setBirthday(dependentDTO.getBirthday());

            employeeDependentRepository.save(dependentEntity);

            return ResponseEntity.ok(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Dependent updated successfully")
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to update dependent: " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> deleteDependent(DependentDTO dependentDTO) {
        try {
            EmployeeEntity employee = securityUtils.getCurrentEmployee();

            EmployeeDependentEntity dependentEntity = employeeDependentRepository.findByIdAndEmployeeId(dependentDTO.getId(), employee.getId());
            if (dependentEntity == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ApiResponseDTO.builder()
                                .status("ERROR")
                                .message("Dependent not found")
                                .build()
                );
            }

            employeeDependentRepository.delete(dependentEntity);

            return ResponseEntity.ok(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Dependent deleted successfully")
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to delete dependent: " + e.getMessage())
                            .build()
            );
        }
    }
}
