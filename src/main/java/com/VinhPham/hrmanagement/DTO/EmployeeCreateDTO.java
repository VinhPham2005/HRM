package com.VinhPham.hrmanagement.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EmployeeCreateDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Birthday is required")
    private String birthday;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^0\\d{9}$", message = "Invalid phone number")
    private String phoneNumber;

    private List<EducationLevelDTO> educationLevels;
    private List<DependentDTO> dependents;
    private List<Long> insuranceIds;
}
