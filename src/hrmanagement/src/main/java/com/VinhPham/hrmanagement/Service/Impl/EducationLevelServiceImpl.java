package com.VinhPham.hrmanagement.Service.Impl;

import com.VinhPham.hrmanagement.Converter.EducationLevelDTOConverter;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.EducationLevelDTO;
import com.VinhPham.hrmanagement.Entity.EducationLevelEntity;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import com.VinhPham.hrmanagement.Repository.EducationLevelRepository;
import com.VinhPham.hrmanagement.Service.EducationLevelService;
import com.VinhPham.hrmanagement.Utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationLevelServiceImpl implements EducationLevelService {
    @Autowired
    EducationLevelRepository educationLevelRepository;

    @Autowired
    SecurityUtils securityUtils;

    @Autowired
    EducationLevelDTOConverter educationLevelDTOConverter;

    @Override
    public List<EducationLevelDTO> getAllEducationLevels() {
        EmployeeEntity employee = securityUtils.getCurrentEmployee();
        return educationLevelDTOConverter.convertToEducationLevelDTOList(employee.getEducationLevels());
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> createEducationLevel(EducationLevelDTO educationLevelDTO) {
        EmployeeEntity employee = securityUtils.getCurrentEmployee();

        EducationLevelEntity educationLevelEntity = EducationLevelEntity.builder()
                .institution(educationLevelDTO.getInstitution())
                .major(educationLevelDTO.getMajor())
                .qualification(educationLevelDTO.getQualification())
                .employee(employee)
                .build();

        educationLevelRepository.save(educationLevelEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseDTO.builder()
                        .status("SUCCESS")
                        .message("Education level created successfully")
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> editEducationLevel(EducationLevelDTO educationLevelDTO) {
        try {
            EmployeeEntity employee = securityUtils.getCurrentEmployee();

            EducationLevelEntity educationLevel = educationLevelRepository.findByIdAndEmployeeId(educationLevelDTO.getId(), employee.getId());
            if (educationLevel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ApiResponseDTO.builder()
                                .status("ERROR")
                                .message("Education level not found")
                                .build()
                );
            }

            educationLevel.setInstitution(educationLevelDTO.getInstitution());
            educationLevel.setMajor(educationLevelDTO.getMajor());
            educationLevel.setQualification(educationLevelDTO.getQualification());

            educationLevelRepository.save(educationLevel);
            return ResponseEntity.ok(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Education level updated successfully")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to update education level: " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> deleteEducationLevel(EducationLevelDTO educationLevelDTO) {
        try {
            EmployeeEntity employee = securityUtils.getCurrentEmployee();

            EducationLevelEntity educationLevel = educationLevelRepository.findByIdAndEmployeeId(educationLevelDTO.getId(), employee.getId());
            if (educationLevel == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ApiResponseDTO.builder()
                                .status("ERROR")
                                .message("Education level not found")
                                .build()
                );
            }

            educationLevelRepository.delete(educationLevel);
            return ResponseEntity.ok(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Education level deleted successfully")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to delete education level: " + e.getMessage())
                            .build()
            );
        }
    }
}
