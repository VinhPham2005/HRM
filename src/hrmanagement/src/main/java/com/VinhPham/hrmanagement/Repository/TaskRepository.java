package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByManagerId(Long managerId);
}

