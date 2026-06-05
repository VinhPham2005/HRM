package com.VinhPham.hrmanagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeAccountCreateDTO {
    private String email;
    private String username;
    private String password;
}
