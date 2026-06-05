package com.VinhPham.hrmanagement.Service;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.EducationLevelDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EducationLevelService {
    List<EducationLevelDTO> getAllEducationLevels();
    ResponseEntity<ApiResponseDTO<?>> createEducationLevel(EducationLevelDTO educationLevelDTO);
    ResponseEntity<ApiResponseDTO<?>> editEducationLevel(EducationLevelDTO educationLevelDTO);
    ResponseEntity<ApiResponseDTO<?>> deleteEducationLevel(EducationLevelDTO educationLevelDTO);
}
