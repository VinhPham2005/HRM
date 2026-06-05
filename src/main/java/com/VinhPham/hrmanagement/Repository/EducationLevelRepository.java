package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.Entity.EducationLevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationLevelRepository extends JpaRepository<EducationLevelEntity, Long> {
    EducationLevelEntity findByIdAndEmployeeId(Long id, Long employeeId);
}

