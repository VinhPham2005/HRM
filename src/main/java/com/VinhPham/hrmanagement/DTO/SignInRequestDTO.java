package com.VinhPham.hrmanagement.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInRequestDTO {
    @NotNull(message = "Username is required!")
    private String username;

    @NotNull(message = "Password is required!")
    private String password;
}
