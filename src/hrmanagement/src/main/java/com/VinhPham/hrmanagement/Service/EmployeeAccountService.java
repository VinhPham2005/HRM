package com.VinhPham.hrmanagement.Service;

import com.VinhPham.hrmanagement.DTO.AccountUpdateDTO;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.EmployeeAccountCreateDTO;
import com.VinhPham.hrmanagement.Entity.EmployeeAccountEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface EmployeeAccountService {
    EmployeeAccountEntity findByUsername(String username) throws UsernameNotFoundException;
    EmployeeAccountEntity findByEmail(String email);
    boolean existsByUsername(String username);
    void save(EmployeeAccountEntity employeeAccountEntity);
    EmployeeAccountEntity createEmployeeAccount(EmployeeAccountCreateDTO employeeAccountCreateDTO);
    ResponseEntity<ApiResponseDTO<?>> changePassword(AccountUpdateDTO accountUpdateDTO);
    ResponseEntity<ApiResponseDTO<?>> changeUsername(AccountUpdateDTO accountUpdateDTO);
}
