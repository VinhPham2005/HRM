package com.VinhPham.hrmanagement.Controller;

import com.VinhPham.hrmanagement.DTO.DashboardDTO;
import com.VinhPham.hrmanagement.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardAPI {
    @Autowired
    private DashboardService dashboardService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public DashboardDTO getDashboard() {
        return dashboardService.getDashboard();
    }
}
