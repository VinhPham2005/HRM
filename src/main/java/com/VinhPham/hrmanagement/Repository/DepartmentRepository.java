package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.DTO.DepartmentStatDTO;
import com.VinhPham.hrmanagement.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    DepartmentEntity findByName(String name);
}

