package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.DTO.DepartmentStatDTO;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import com.VinhPham.hrmanagement.Repository.Custom.EmployeeRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, EmployeeRepositoryCustom {
    List<EmployeeEntity> findByNameContainingIgnoringCase(String name);

    List<EmployeeEntity> findByDepartmentId(Long departmentId);

    Page<EmployeeEntity> findByDepartmentId(Long departmentId, Pageable pageable);

    List<EmployeeEntity> findByPositionId(Long positionId);

    Page<EmployeeEntity> findByPositionId(Long departmentId, Pageable pageable);

    @Query("""
                SELECT new com.VinhPham.hrmanagement.DTO.DepartmentStatDTO(
                    e.department.name,
                    COUNT(e),
                    AVG(p.salary)
                )
                FROM EmployeeEntity e
                JOIN e.payrolls p
                GROUP BY e.department.name
            """)
    List<DepartmentStatDTO> getDepartmentStat();

    @Query("""
                SELECT AVG(p.salary)
                FROM PayrollEntity p
            """)
    Double getAverageSalary();

    @Query("""
                SELECT AVG(p.salary)
                FROM PayrollEntity p
                WHERE p.employee.department.id = :departmentId
            """)
    Double getAverageSalaryByDepartment();
}
