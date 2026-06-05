package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.Entity.TimesheetManagementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetManagementRepository extends JpaRepository<TimesheetManagementEntity, Long> {
    List<TimesheetManagementEntity> findByEmployeeId(Long employeeId);

    @Query("""
                SELECT t FROM TimesheetManagementEntity t
                WHERE t.employee.id = :employeeId
                AND t.date BETWEEN :start AND :end
            """)
    List<TimesheetManagementEntity> findByEmployeeAndMonth(@Param("employeeId") Long employeeId,
                                                           @Param("start") LocalDate start,
                                                           @Param("end") LocalDate end
    );
}

