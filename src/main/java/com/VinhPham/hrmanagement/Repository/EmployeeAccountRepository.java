package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.Entity.EmployeeAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccountEntity, Long> {
    Optional<EmployeeAccountEntity> findByUsername(String username);
    Optional<EmployeeAccountEntity> findByEmail(String email);
    Optional<EmployeeAccountEntity> findByEmployeeId(Long employeeId);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}

