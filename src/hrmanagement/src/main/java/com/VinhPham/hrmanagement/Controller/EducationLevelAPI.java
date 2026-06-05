package com.VinhPham.hrmanagement.Controller;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.EducationLevelDTO;
import com.VinhPham.hrmanagement.Service.EducationLevelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal-info/education-level")
public class EducationLevelAPI {
    @Autowired
    EducationLevelService educationLevelService;

    @GetMapping
    public List<EducationLevelDTO> getAllEducationLevels() {
        return educationLevelService.getAllEducationLevels();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<?>> createEducationLevel(@RequestBody @Valid EducationLevelDTO educationLevelDTO) {
        return educationLevelService.createEducationLevel(educationLevelDTO);
    }

    @PatchMapping("/edit")
    public ResponseEntity<ApiResponseDTO<?>> editEducationLevel(@RequestBody EducationLevelDTO educationLevelDTO) {
        return educationLevelService.editEducationLevel(educationLevelDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponseDTO<?>> deleteEducationLevel(@RequestBody EducationLevelDTO educationLevelDTO) {
        return educationLevelService.deleteEducationLevel(educationLevelDTO);
    }
}
