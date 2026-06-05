package com.VinhPham.hrmanagement.DTO;

import com.VinhPham.hrmanagement.ENUM.ApprovalStatusEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveManagementDTO {
    private Long id;
    @NotBlank
    private String leaveType;
    @NotBlank
    private LocalDate startDate;
    @NotBlank
    private LocalDate endDate;

    @NotBlank(message = "Reason is required!")
    private String reason;
    private ApprovalStatusEnum approvalStatus;
}
