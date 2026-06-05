package com.VinhPham.hrmanagement.Service.Impl;

import com.VinhPham.hrmanagement.Converter.DepartmentDTOConverter;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.DepartmentDTO;
import com.VinhPham.hrmanagement.Entity.DepartmentEntity;
import com.VinhPham.hrmanagement.Repository.DepartmentRepository;
import com.VinhPham.hrmanagement.Service.DepartmentService;
import com.VinhPham.hrmanagement.Utils.DateFormatter;
import com.VinhPham.hrmanagement.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    DepartmentDTOConverter departmentDTOConverter;

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity>  departmentEntityList = departmentRepository.findAll();
        return departmentDTOConverter.convertToDepartmentDTOList(departmentEntityList);
    }

    @Override
    public DepartmentDTO getDepartmentById(Long id) {
        return departmentDTOConverter.convertToDepartmentDTO(departmentRepository.findById(id).orElse(null));
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> createDepartment(DepartmentDTO departmentCreateDTO) {
        try {
            DepartmentEntity departmentEntity = DepartmentEntity.builder()
                    .name(departmentCreateDTO.getName())
                    .foundedDate(DateFormatter.normalizeDate(departmentCreateDTO.getFoundedDate()))
                    .build();
            departmentRepository.save(departmentEntity);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Department created successfully")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to create department: " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> editDepartment(DepartmentDTO departmentUpdateDTO) {
        try {
            DepartmentEntity departmentEntity = departmentRepository.findById(departmentUpdateDTO.getId()).orElseThrow(() -> new RuntimeException("Department not found"));

            if (!StringUtils.isNullOrEmpty(departmentUpdateDTO.getName())) {
                departmentEntity.setName(departmentUpdateDTO.getName());
            }

            if (!StringUtils.isNullOrEmpty(departmentUpdateDTO.getFoundedDate())) {
                departmentEntity.setFoundedDate(DateFormatter.normalizeDate(departmentUpdateDTO.getFoundedDate()));
            }

            departmentRepository.save(departmentEntity);
            return ResponseEntity.ok(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Department updated successfully")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to update department: " + e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> deleteDepartment(Long id) {
        try {
            departmentRepository.deleteById(id);
            return ResponseEntity.ok(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Department deleted successfully")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to delete department: " + e.getMessage())
                            .build()
            );
        }
    }
}
