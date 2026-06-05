package com.VinhPham.hrmanagement.Service;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.DependentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DependentService {
    List<DependentDTO> getAllDependents();
    ResponseEntity<ApiResponseDTO<?>> createDependent(DependentDTO dependentDTO);
    ResponseEntity<ApiResponseDTO<?>> updateDependent(DependentDTO dependentDTO);
    ResponseEntity<ApiResponseDTO<?>> deleteDependent(DependentDTO dependentDTO);
}
