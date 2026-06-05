package com.VinhPham.hrmanagement.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountUpdateDTO {
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Current password is required")
    private String curPassword;

    private String newUsername;

    private String newPassword;
}
