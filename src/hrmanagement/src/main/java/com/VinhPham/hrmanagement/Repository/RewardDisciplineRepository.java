package com.VinhPham.hrmanagement.Repository;

import com.VinhPham.hrmanagement.DTO.RankingDTO;
import com.VinhPham.hrmanagement.ENUM.RewardTypeEnum;
import com.VinhPham.hrmanagement.Entity.RewardDisciplineEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RewardDisciplineRepository extends JpaRepository<RewardDisciplineEntity, Long> {
    List<RewardDisciplineEntity> findByEmployeeId(Long employeeId);

    @Query("""
            SELECT SUM(r.amount)
            FROM RewardDisciplineEntity r
            WHERE r.type = :type
            """)
    BigDecimal sumAmountByType(@Param("type") RewardTypeEnum Type);

    @Query("""
            SELECT new com.VinhPham.hrmanagement.DTO.RankingDTO(e.id, e.name, d.name, SUM(r.amount))
            FROM RewardDisciplineEntity r
            JOIN r.employee e
            JOIN e.department d
            WHERE r.type = :type
            GROUP BY e.id, e.name, d.name
            ORDER BY SUM(r.amount) DESC
            """)
    List<RankingDTO> getTopEmployeeByType(@Param("type") RewardTypeEnum type, Pageable pageable);

    @Query("""
                SELECT r FROM RewardDisciplineEntity r
                WHERE r.employee.id = :employeeId
                AND r.decisionDate BETWEEN :start AND :end
            """)
    List<RewardDisciplineEntity> findByEmployeeAndMonth(
            Long employeeId,
            LocalDate start,
            LocalDate end
    );
}

