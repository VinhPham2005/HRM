package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.Entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    PositionEntity findByPositionName(String name);
}

