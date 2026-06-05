package com.VinhPham.hrmanagement.Converter;

import com.VinhPham.hrmanagement.DTO.DependentDTO;
import com.VinhPham.hrmanagement.DTO.EducationLevelDTO;
import com.VinhPham.hrmanagement.Entity.EducationLevelEntity;
import com.VinhPham.hrmanagement.Entity.EmployeeDependentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EducationLevelDTOConverter {
    @Autowired
    ModelMapper modelMapper;

    public EducationLevelDTO convertToDependentDTO(EducationLevelEntity educationLevel) {
        return modelMapper.map(educationLevel, EducationLevelDTO.class);
    }

    public List<EducationLevelDTO> convertToEducationLevelDTOList(List<EducationLevelEntity> educationLevels) {
        List<EducationLevelDTO> educationLevelList = new ArrayList<>();
        for (EducationLevelEntity educationLevel : educationLevels) {
            EducationLevelDTO educationLevelDTO = modelMapper.map(educationLevel, EducationLevelDTO.class);
            educationLevelList.add(educationLevelDTO);
        }
        return educationLevelList;
    }
}
