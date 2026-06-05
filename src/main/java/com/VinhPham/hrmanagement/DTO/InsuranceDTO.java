package com.VinhPham.hrmanagement.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InsuranceDTO {
    private Long id;

    @NotBlank(message = "Insurance name is required")
    private String name;
}
