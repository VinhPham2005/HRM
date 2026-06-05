package com.VinhPham.hrmanagement.Controller;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.LeaveManagementDTO;
import com.VinhPham.hrmanagement.Service.LeaveManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave-letter")
public class LeaveManagementAPI {
    @Autowired
    private LeaveManagementService leaveManagementService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<?>> createLeave(LeaveManagementDTO leaveManagementDTO) {
        return leaveManagementService.createLeave(leaveManagementDTO);
    }

    @PatchMapping("/edit")
    public ResponseEntity<ApiResponseDTO<?>> editLeave(LeaveManagementDTO leaveManagementDTO) {
        return leaveManagementService.editLeave(leaveManagementDTO);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/leave-management")
    public List<LeaveManagementDTO> getLeaveManagement() {
        return leaveManagementService.findByDepartment();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/leave-management/approval")
    public ResponseEntity<ApiResponseDTO<?>> approveOrReject(LeaveManagementDTO leaveManagementDTO) {
        return leaveManagementService.approveOrReject(leaveManagementDTO);
    }
}
