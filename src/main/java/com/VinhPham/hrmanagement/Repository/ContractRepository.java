package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.Entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    List<ContractEntity> findByEmployeeId(Long employeeId);

    @Query("""
                SELECT c FROM ContractEntity c
                WHERE c.employee.id = :employeeId
                AND c.startDate <= :endDate
                AND (c.endDate IS NULL OR c.endDate >= :startDate)
            """)
    Optional<ContractEntity> findActiveContract(@Param("employeeId") Long employeeId,
                                                @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
}

