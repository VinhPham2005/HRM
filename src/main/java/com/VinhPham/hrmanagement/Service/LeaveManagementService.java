package com.VinhPham.hrmanagement.Service;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.LeaveManagementDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LeaveManagementService {
    ResponseEntity<ApiResponseDTO<?>> createLeave(LeaveManagementDTO leaveManagementDTO);
    List<LeaveManagementDTO> findByDepartment();
    ResponseEntity<ApiResponseDTO<?>> editLeave(LeaveManagementDTO leaveManagementDTO);
    ResponseEntity<ApiResponseDTO<?>> approveOrReject(LeaveManagementDTO leaveManagementDTO);
}
