package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.Entity.EmployeeDependentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDependentRepository extends JpaRepository<EmployeeDependentEntity, Long> {
    List<EmployeeDependentEntity> findByEmployeeId(Long employeeId);
    EmployeeDependentEntity findByIdAndEmployeeId(Long id, Long employeeId);
}

