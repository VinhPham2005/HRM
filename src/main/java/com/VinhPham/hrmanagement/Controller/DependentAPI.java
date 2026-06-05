package com.VinhPham.hrmanagement.Controller;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.DependentDTO;
import com.VinhPham.hrmanagement.Service.DependentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personal-info/dependent")
public class DependentAPI {
    @Autowired
    private DependentService dependentService;

    @GetMapping
    public List<DependentDTO> getAllDependents() {
        return dependentService.getAllDependents();
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDTO<?>> createDependent(@RequestBody @Valid DependentDTO dependentDTO) {
        return dependentService.createDependent(dependentDTO);
    }

    @PatchMapping("/edit")
    public ResponseEntity<ApiResponseDTO<?>> updateDependent(@RequestBody DependentDTO dependentDTO) {
        return dependentService.updateDependent(dependentDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponseDTO<?>> deleteDependent(@RequestBody DependentDTO dependentDTO) {
        return dependentService.deleteDependent(dependentDTO);
    }
}
