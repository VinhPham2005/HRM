package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.Entity.TaskAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskAssignmentRepository extends JpaRepository<TaskAssignmentEntity, Long> {
    List<TaskAssignmentEntity> findByStaffId(Long staffId);
    List<TaskAssignmentEntity> findByTaskId(Long taskId);
}

