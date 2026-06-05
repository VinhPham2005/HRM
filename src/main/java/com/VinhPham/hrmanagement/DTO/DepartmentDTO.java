package com.VinhPham.hrmanagement.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentDTO {
    @JsonIgnore
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Founded date is required")
    private String foundedDate;
}
