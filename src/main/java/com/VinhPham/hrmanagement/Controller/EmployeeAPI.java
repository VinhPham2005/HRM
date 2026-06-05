package com.VinhPham.hrmanagement.Controller;

import com.VinhPham.hrmanagement.CustomException.UserAlreadyExistsException;
import com.VinhPham.hrmanagement.DTO.*;
import com.VinhPham.hrmanagement.Security.Service.AuthService;
import com.VinhPham.hrmanagement.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeAPI {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    private AuthService authService;

    @GetMapping("/employees/id/{id}")
    public EmployeeDTO findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/employees")
    public List<EmployeeDTO> findAll() {
        return employeeService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/employees/paginated")
    public Page<EmployeeDTO> findAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return employeeService.findAllWithPagination(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/departments/{departmentId}/employees")
    public List<EmployeeDTO> findByDepartmentId(@PathVariable Long departmentId) {
        return employeeService.findByDepartmentId(departmentId);
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
    @GetMapping("/departments/{departmentId}/employees/paginated")
    public Page<EmployeeDTO> findByDepartmentIdPaginated(
            @PathVariable Long departmentId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return employeeService.findByDepartmentIdWithPagination(departmentId, pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/employees/name/{name}")
    public List<EmployeeDTO> findByName(@PathVariable String name) {
        return employeeService.findByName(name);
    }

    @GetMapping("/employees/search")
    public List<EmployeeDTO> searchByName(@ModelAttribute EmployeeSearchDTO employeeSearchDTO) {
        return employeeService.findEmployees(employeeSearchDTO);
    }

    @GetMapping("/employees/personal-info")
    public EmployeeDTO getPersonalInfo() {
        return employeeService.getPersonalInfo();
    }

    @PatchMapping("/employees/personal-info/edit")
    public ResponseEntity<ApiResponseDTO<?>> editPersonalInfo(@RequestBody EmployeeUpdateDTO employeeUpdateDTO) {
        return employeeService.editPersonalInfo(employeeUpdateDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<ApiResponseDTO<?>> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponseDTO<?>> registerUser(@RequestBody @Valid SignUpRequestDTO signUpRequestDto)
            throws UserAlreadyExistsException {
        return authService.signUp(signUpRequestDto);
    }
}
