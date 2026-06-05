package com.VinhPham.hrmanagement.Repository.Custom;

import com.VinhPham.hrmanagement.DTO.EmployeeSearchDTO;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepositoryCustom {
    List<EmployeeEntity> findEmployees(EmployeeSearchDTO employeeSearchDTO);

}
