package com.VinhPham.hrmanagement.Entity;

import com.VinhPham.hrmanagement.Audit.BaseAudit;
import com.VinhPham.hrmanagement.ENUM.ApprovalStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
@Table(name = "leave_management")
public class LeaveManagementEntity extends BaseAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "leave_type", length = 100)
    private String leaveType;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;

    @Column(name = "approval_status", length = 50)
    @Enumerated(EnumType.STRING)
    private ApprovalStatusEnum approvalStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;
}

