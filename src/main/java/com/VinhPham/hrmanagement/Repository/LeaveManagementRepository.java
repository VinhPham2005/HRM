package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.Entity.LeaveManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveManagementRepository extends JpaRepository<LeaveManagementEntity, Long> {
    List<LeaveManagementEntity> findByEmployeeId(Long employeeId);

    @Query("""
                SELECT l
                FROM LeaveManagementEntity l
                JOIN l.employee e
                JOIN e.department d
                WHERE d.id = :departmentId
            """)
    List<LeaveManagementEntity> findByDepartment(@Param("departmentId") Long departmentId);
}

