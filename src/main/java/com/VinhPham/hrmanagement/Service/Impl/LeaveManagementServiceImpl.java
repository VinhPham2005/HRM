package com.VinhPham.hrmanagement.Service.Impl;

import com.VinhPham.hrmanagement.Converter.LeaveManagementConverter;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.LeaveManagementDTO;
import com.VinhPham.hrmanagement.ENUM.ApprovalStatusEnum;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import com.VinhPham.hrmanagement.Entity.LeaveManagementEntity;
import com.VinhPham.hrmanagement.Repository.LeaveManagementRepository;
import com.VinhPham.hrmanagement.Service.LeaveManagementService;
import com.VinhPham.hrmanagement.Utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveManagementServiceImpl implements LeaveManagementService {
    @Autowired
    LeaveManagementRepository leaveManagementRepository;

    @Autowired
    LeaveManagementConverter leaveManagementConverter;

    @Autowired
    SecurityUtils securityUtils;

    @Override
    public ResponseEntity<ApiResponseDTO<?>> createLeave(LeaveManagementDTO leaveManagementDTO) {
        EmployeeEntity employee = securityUtils.getCurrentEmployee();

        LeaveManagementEntity leave = LeaveManagementEntity.builder()
                .employee(employee)
                .leaveType(leaveManagementDTO.getLeaveType())
                .startDate(leaveManagementDTO.getStartDate())
                .endDate(leaveManagementDTO.getEndDate())
                .reason(leaveManagementDTO.getReason())
                .approvalStatus(ApprovalStatusEnum.PENDING)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseDTO.builder()
                        .status("CREATED")
                        .message("Created!")
                        .build()
        );
    }

    @Override
    public List<LeaveManagementDTO> findByDepartment() {
        EmployeeEntity employee = securityUtils.getCurrentEmployee();
        Long departmentId = employee.getDepartment().getId();
        return leaveManagementConverter.convertToLeaveManagementDTOList(leaveManagementRepository.findByDepartment(departmentId));
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> editLeave(LeaveManagementDTO leaveManagementDTO) {
        Long id = leaveManagementDTO.getId();

        LeaveManagementEntity leaveManagementEntity = leaveManagementRepository.findById(id).orElse(null);

        if (leaveManagementEntity == null) {
            return ResponseEntity.badRequest().body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Can not find leave letter!")
                            .build()
            );
        }

        leaveManagementEntity.setLeaveType(leaveManagementDTO.getLeaveType());
        leaveManagementEntity.setReason(leaveManagementDTO.getReason());
        leaveManagementEntity.setStartDate(leaveManagementDTO.getStartDate());
        leaveManagementEntity.setEndDate(leaveManagementDTO.getEndDate());
        leaveManagementEntity.setReason(leaveManagementEntity.getReason());


        leaveManagementRepository.save(leaveManagementEntity);

        return ResponseEntity.ok(
                ApiResponseDTO.builder()
                        .status("SUCCESS")
                        .message("Update successfully!")
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> approveOrReject(LeaveManagementDTO leaveManagementDTO) {
        Long id = leaveManagementDTO.getId();

        LeaveManagementEntity leaveManagementEntity = leaveManagementRepository.findById(id).orElse(null);

        if (leaveManagementEntity == null) {
            return ResponseEntity.badRequest().body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Can not find leave letter!")
                            .build()
            );
        }

        leaveManagementEntity.setApprovalStatus(leaveManagementDTO.getApprovalStatus());

        leaveManagementRepository.save(leaveManagementEntity);

        return ResponseEntity.ok(
                ApiResponseDTO.builder()
                        .status("SUCCESS")
                        .message("Update successfully!")
                        .build()
        );
    }
}
