package com.VinhPham.hrmanagement.Utils;

import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import com.VinhPham.hrmanagement.Service.EmployeeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    @Autowired
    private EmployeeAccountService employeeAccountService;

    public EmployeeEntity getCurrentEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return employeeAccountService.findByEmail(email).getEmployee();
    }
}
