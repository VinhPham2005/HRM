package com.VinhPham.hrmanagement.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DependentDTO {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Relationship is required")
    private String relationship;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    @JsonIgnore
    private LocalDate deductionStartDate;

    @JsonIgnore
    private LocalDate deductionEndDate;

}
