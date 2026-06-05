package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.Entity.EmployeeInsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeInsuranceRepository extends JpaRepository<EmployeeInsuranceEntity, Long> {
    List<EmployeeInsuranceEntity> findByEmployeeId(Long employeeId);
    EmployeeInsuranceEntity findByEmployeeIdAndInsuranceId(Long employeeId, Long insuranceId);
}

