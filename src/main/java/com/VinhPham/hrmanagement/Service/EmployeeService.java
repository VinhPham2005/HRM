package com.VinhPham.hrmanagement.Service;

import com.VinhPham.hrmanagement.CustomException.EmailAlreadyExistsException;
import com.VinhPham.hrmanagement.DTO.*;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO findById(Long id);
    EmployeeDTO getPersonalInfo();
    List<EmployeeDTO> findByDepartmentId(Long departmentId);
    List<EmployeeDTO> findByName(String name);
    List<EmployeeDTO> findAll();
    List<EmployeeDTO> findEmployees(EmployeeSearchDTO employeeSearchDTO);
    EmployeeEntity createEmployee(EmployeeCreateDTO employeeCreateDTO);
    ResponseEntity<ApiResponseDTO<?>> editPersonalInfo(EmployeeUpdateDTO employeeUpdateDTO);
    ResponseEntity<ApiResponseDTO<?>> deleteEmployee(Long id);

    Page<EmployeeDTO> findAllWithPagination(Pageable pageable);
    Page<EmployeeDTO> findByDepartmentIdWithPagination(Long departmentId, Pageable pageable);
    Page<EmployeeDTO> findByPositionIdWithPagination(Long positionId, Pageable pageable);

    ResponseEntity<ApiResponseDTO<?>> save(EmployeeEntity employeeEntity);
}
