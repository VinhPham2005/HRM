package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.Entity.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long> {
    InsuranceEntity findByName(String name);
}

