package com.VinhPham.hrmanagement.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeDTO {
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    private String gender;
    private String address;
    private String phoneNumber;
    private String departmentName;
    private String positionName;
    private String accountUsername;
    private List<String> institution;
    private List<String> major;
    private List<EducationLevelDTO> educationLevels;
    private List<DependentDTO> employeeDependents;
    private List<String> insurances;
}
