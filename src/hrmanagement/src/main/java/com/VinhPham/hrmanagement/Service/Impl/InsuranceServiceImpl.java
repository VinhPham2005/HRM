package com.VinhPham.hrmanagement.Service.Impl;

import com.VinhPham.hrmanagement.Converter.InsuranceDTOConverter;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.InsuranceDTO;
import com.VinhPham.hrmanagement.Entity.InsuranceEntity;
import com.VinhPham.hrmanagement.Repository.InsuranceRepository;
import com.VinhPham.hrmanagement.Service.InsuranceService;
import com.VinhPham.hrmanagement.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    InsuranceDTOConverter insuranceDTOConverter;

    @Override
    public List<InsuranceDTO> getAllInsurances() {
        List<InsuranceEntity> insuranceEntityList = insuranceRepository.findAll();
        return insuranceDTOConverter.convertToInsuranceDTOList(insuranceEntityList);
    }

    @Override
    public InsuranceDTO getInsuranceById(Long id) {
        return insuranceDTOConverter.convertToInsuranceDTO(insuranceRepository.findById(id).orElse(null));
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> createInsurance(InsuranceDTO insuranceDTO) {
        try {
            InsuranceEntity existingInsurance = insuranceRepository.findByName(insuranceDTO.getName());
            if (existingInsurance != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                        ApiResponseDTO.builder()
                                .status("ERROR")
                                .message("Insurance with name '" + insuranceDTO.getName() + "' already exists")
                                .build()
                );
            }

            InsuranceEntity insuranceEntity = InsuranceEntity.builder()
                    .name(insuranceDTO.getName())
                    .build();
            insuranceRepository.save(insuranceEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Insurance created successfully")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to create insurance: " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> editInsurance(InsuranceDTO insuranceDTO) {
        try {
            InsuranceEntity insuranceEntity = insuranceRepository.findById(insuranceDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Insurance not found"));

            if (!StringUtils.isNullOrEmpty(insuranceDTO.getName())) {
                InsuranceEntity existingInsurance = insuranceRepository.findByName(insuranceDTO.getName());
                if (existingInsurance != null && !existingInsurance.getId().equals(insuranceDTO.getId())) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(
                            ApiResponseDTO.builder()
                                    .status("ERROR")
                                    .message("Insurance with name '" + insuranceDTO.getName() + "' already exists")
                                    .build()
                    );
                }
                insuranceEntity.setName(insuranceDTO.getName());
            }

            insuranceRepository.save(insuranceEntity);
            return ResponseEntity.ok(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Insurance updated successfully")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to update insurance: " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> deleteInsurance(Long id) {
        try {
            if (!insuranceRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ApiResponseDTO.builder()
                                .status("ERROR")
                                .message("Insurance not found with id: " + id)
                                .build()
                );
            }

            insuranceRepository.deleteById(id);
            return ResponseEntity.ok(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Insurance deleted successfully")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to delete insurance: " + e.getMessage())
                            .build()
            );
        }
    }
}
