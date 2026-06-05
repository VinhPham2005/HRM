package com.VinhPham.hrmanagement.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EducationLevelDTO {
//    @JsonIgnore
    private Long id;

    @NotBlank(message = "Institution is required")
    private String institution;

    @NotBlank(message = "Major is required")
    private String major;

    @NotBlank(message = "Qualification is required")
    private String qualification;
}
