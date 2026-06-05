package com.VinhPham.hrmanagement.Repository.Custom.Impl;

import com.VinhPham.hrmanagement.DTO.EmployeeSearchDTO;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import com.VinhPham.hrmanagement.Repository.Custom.EmployeeRepositoryCustom;
import com.VinhPham.hrmanagement.Utils.NumberUtil;
import com.VinhPham.hrmanagement.Utils.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<EmployeeEntity> findEmployees(EmployeeSearchDTO employeeSearchDTO) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EmployeeEntity> query = cb.createQuery(EmployeeEntity.class);
        Root<EmployeeEntity> root = query.from(EmployeeEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if(!NumberUtil.isNull(employeeSearchDTO.getId())) {
            predicates.add(cb.equal(root.get("id"), employeeSearchDTO.getId()));
        }

        if(!StringUtils.isNullOrEmpty(employeeSearchDTO.getName())) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + employeeSearchDTO.getName().toLowerCase() + "%"));
        }

        if(!StringUtils.isNullOrEmpty(employeeSearchDTO.getPhoneNumber())) {
            predicates.add(cb.like(cb.lower(root.get("phoneNumber")), "%" + employeeSearchDTO.getPhoneNumber().toLowerCase() + "%"));
        }

        if(!NumberUtil.isNull(employeeSearchDTO.getDepartmentId())) {
            predicates.add(cb.equal(root.get("department").get("id"), employeeSearchDTO.getDepartmentId()));
        }

        if(!predicates.isEmpty()) {
            query.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        return entityManager.createQuery(query).getResultList();
    }

}
