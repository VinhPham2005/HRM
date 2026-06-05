package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import com.VinhPham.hrmanagement.Entity.PayrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<PayrollEntity, Long> {
    @Query("""
                SELECT p FROM PayrollEntity p
                WHERE p.month = :month
            """)
    List<PayrollEntity> findAllActivePayroll(@Param("month") LocalDate month);

    PayrollEntity findByEmployeeIdAndMonth(Long employeeId, LocalDate month);
    boolean existsByEmployeeAndMonth(EmployeeEntity employee, LocalDate month);
}

