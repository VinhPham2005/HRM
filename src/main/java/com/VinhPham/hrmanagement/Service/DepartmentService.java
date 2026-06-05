package com.VinhPham.hrmanagement.Service;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.DepartmentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getAllDepartments();
    DepartmentDTO getDepartmentById(Long id);
    ResponseEntity<ApiResponseDTO<?>> createDepartment(DepartmentDTO departmentDTO);
    ResponseEntity<ApiResponseDTO<?>> editDepartment(DepartmentDTO departmentDTO);
    ResponseEntity<ApiResponseDTO<?>> deleteDepartment(Long id);
}
