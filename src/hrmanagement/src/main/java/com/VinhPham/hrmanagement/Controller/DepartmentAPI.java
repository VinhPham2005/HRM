package com.VinhPham.hrmanagement.Controller;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.DepartmentDTO;
import com.VinhPham.hrmanagement.Service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class DepartmentAPI {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departments")
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/departments/id/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/departments/create")
    public ResponseEntity<ApiResponseDTO<?>> createDepartment(@Valid @RequestBody  DepartmentDTO departmentDTO) {
        return departmentService.createDepartment(departmentDTO);
    }

    @PatchMapping("/departments/edit")
    public ResponseEntity<ApiResponseDTO<?>> editDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.editDepartment(departmentDTO);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<ApiResponseDTO<?>> deleteDepartment(@PathVariable Long id) {
        return departmentService.deleteDepartment(id);
    }
}
