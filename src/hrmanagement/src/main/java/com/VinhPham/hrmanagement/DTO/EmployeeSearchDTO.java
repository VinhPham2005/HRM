package com.VinhPham.hrmanagement.DTO;

import lombok.Data;

@Data
public class EmployeeSearchDTO {
    private Long id;
    private String name;
    private String gender;
    private String address;
    private String phoneNumber;
    private Long departmentId;
}
