package com.VinhPham.hrmanagement.Service.Impl;

import com.VinhPham.hrmanagement.Converter.EmployeeDTOConverter;
import com.VinhPham.hrmanagement.CustomException.EmailAlreadyExistsException;
import com.VinhPham.hrmanagement.DTO.*;
import com.VinhPham.hrmanagement.Entity.EducationLevelEntity;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import com.VinhPham.hrmanagement.Repository.DepartmentRepository;
import com.VinhPham.hrmanagement.Repository.EmployeeRepository;
import com.VinhPham.hrmanagement.Repository.PositionRepository;
import com.VinhPham.hrmanagement.Service.EmployeeAccountService;
import com.VinhPham.hrmanagement.Service.EmployeeService;
import com.VinhPham.hrmanagement.Utils.DateFormatter;
import com.VinhPham.hrmanagement.Utils.SecurityUtils;
import com.VinhPham.hrmanagement.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    EmployeeDTOConverter employeeDTOConverter;

    @Autowired
    EmployeeAccountService employeeAccountService;

    @Autowired
    SecurityUtils securityUtils;

    @Override
    public EmployeeDTO findById(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id).orElse(null);
        return employeeDTOConverter.convertToEmployeeDTO(employee);
    }

    @Override
    public EmployeeDTO getPersonalInfo() {
        EmployeeEntity employee = securityUtils.getCurrentEmployee();

        return employeeDTOConverter.convertToEmployeeDTO(employee);
    }

    @Override
    public List<EmployeeDTO> findByDepartmentId(Long departmentId) {
        return employeeDTOConverter.convertToEmployeeDTOList(employeeRepository.findByDepartmentId(departmentId));
    }

    @Override
    public List<EmployeeDTO> findByName(String name) {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findByNameContainingIgnoringCase(name);

        return employeeDTOConverter.convertToEmployeeDTOList(employeeEntityList);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        return employeeDTOConverter.convertToEmployeeDTOList(employeeEntityList);
    }

    @Override
    public List<EmployeeDTO> findEmployees(EmployeeSearchDTO employeeSearchDTO) {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findEmployees(employeeSearchDTO);
        return employeeDTOConverter.convertToEmployeeDTOList(employeeEntityList);
    }

    @Override
    public EmployeeEntity createEmployee(EmployeeCreateDTO employeeCreateDTO){

        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .name(employeeCreateDTO.getName())
                .birthday(DateFormatter.normalizeDate(employeeCreateDTO.getBirthday()))
                .gender(employeeCreateDTO.getGender())
                .phoneNumber(employeeCreateDTO.getPhoneNumber())
                .address(employeeCreateDTO.getAddress())
                .department(departmentRepository.findByName("Trống"))
                .position(positionRepository.findByPositionName("Nhân viên"))
                .build();

        List<EducationLevelEntity> educationLevelEntities = new ArrayList<>();
        if (employeeCreateDTO.getEducationLevels() != null) {

            for (EducationLevelDTO edu : employeeCreateDTO.getEducationLevels()) {
                EducationLevelEntity newEdu = EducationLevelEntity.builder()
                        .major(edu.getMajor())
                        .institution(edu.getInstitution())
                        .qualification(edu.getQualification())
                        .employee(employeeEntity)
                        .build();

                educationLevelEntities.add(newEdu);
            }
        }

        employeeEntity.setEducationLevels(educationLevelEntities);

        return employeeEntity;
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> editPersonalInfo(EmployeeUpdateDTO employeeUpdateDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            EmployeeEntity employee = employeeAccountService.findByEmail(email).getEmployee();

            if (!StringUtils.isNullOrEmpty(employeeUpdateDTO.getName())) {
                employee.setName(employeeUpdateDTO.getName());
            }
            if (!StringUtils.isNullOrEmpty(employeeUpdateDTO.getBirthday())) {
                employee.setBirthday(DateFormatter.normalizeDate(employeeUpdateDTO.getBirthday()));
            }
            if (!StringUtils.isNullOrEmpty(employeeUpdateDTO.getGender())) {
                employee.setGender(employeeUpdateDTO.getGender());
            }
            if (!StringUtils.isNullOrEmpty(employeeUpdateDTO.getPhoneNumber())) {
                employee.setPhoneNumber(employeeUpdateDTO.getPhoneNumber());
            }
            if (!StringUtils.isNullOrEmpty(employeeUpdateDTO.getAddress())) {
                employee.setAddress(employeeUpdateDTO.getAddress());
            }

            employeeRepository.save(employee);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Employee personal information has been successfully updated!")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to update employee personal information!")
                            .response(e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> deleteEmployee(Long id) {
        try {
            EmployeeEntity employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            employeeRepository.delete(employee);

            return ResponseEntity.ok(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Employee has been successfully deleted!")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to delete employee!")
                            .response(e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public Page<EmployeeDTO> findAllWithPagination(Pageable pageable) {
        Page<EmployeeEntity> employeePage = employeeRepository.findAll(pageable);
        return employeePage.map(employee -> employeeDTOConverter.convertToEmployeeDTO(employee));
    }

    @Override
    public Page<EmployeeDTO> findByDepartmentIdWithPagination(Long departmentId, Pageable pageable) {
        Page<EmployeeEntity> employeePage = employeeRepository.findByDepartmentId(departmentId, pageable);
        return employeePage.map(employee -> employeeDTOConverter.convertToEmployeeDTO(employee));
    }

    @Override
    public Page<EmployeeDTO> findByPositionIdWithPagination(Long positionId, Pageable pageable) {
        Page<EmployeeEntity> employeePage = employeeRepository.findByPositionId(positionId, pageable);
        return employeePage.map(employee -> employeeDTOConverter.convertToEmployeeDTO(employee));
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> save(EmployeeEntity employeeEntity) {
        try {
            employeeRepository.save(employeeEntity);
            return ResponseEntity.ok(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("Employee has been successfully saved!")
                            .build()
            );
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(
                    ApiResponseDTO.builder()
                            .status("ERROR")
                            .message("Failed to save employee!")
                            .response(e.getMessage())
                            .build()
            );
        }
    }


}
