package com.VinhPham.hrmanagement.DTO;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeUpdateDTO {
    private String name;
    private String birthday;
    private String gender;
    private String address;
    private String phoneNumber;
    private List<EducationLevelDTO> educationLevels;
    private List<DependentDTO> dependents;
}
