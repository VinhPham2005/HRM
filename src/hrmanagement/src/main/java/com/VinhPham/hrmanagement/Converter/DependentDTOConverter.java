package com.VinhPham.hrmanagement.Converter;

import com.VinhPham.hrmanagement.DTO.DependentDTO;
import com.VinhPham.hrmanagement.DTO.EmployeeDTO;
import com.VinhPham.hrmanagement.Entity.EmployeeDependentEntity;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DependentDTOConverter {
    @Autowired
    ModelMapper modelMapper;

    public DependentDTO convertToDependentDTO(EmployeeDependentEntity dependent) {
        return modelMapper.map(dependent, DependentDTO.class);
    }

    public List<DependentDTO> convertToDependentDTOList(List<EmployeeDependentEntity> dependents) {
        List<DependentDTO> dependentDTOList = new ArrayList<>();
        for (EmployeeDependentEntity dependent : dependents) {
            DependentDTO dependentDTO = modelMapper.map(dependent, DependentDTO.class);
            dependentDTOList.add(dependentDTO);
        }
        return dependentDTOList;
    }
}
