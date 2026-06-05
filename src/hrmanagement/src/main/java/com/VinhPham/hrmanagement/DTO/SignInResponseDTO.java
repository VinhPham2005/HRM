package com.VinhPham.hrmanagement.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SignInResponseDTO {
    private String token;
    private String type = "Bearer";
    private String username;
    private List<String> roles;
}
