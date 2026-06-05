package com.VinhPham.hrmanagement.Converter;

import com.VinhPham.hrmanagement.DTO.DepartmentDTO;
import com.VinhPham.hrmanagement.Entity.DepartmentEntity;
import com.VinhPham.hrmanagement.Repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentDTOConverter {
    @Autowired
    ModelMapper modelMapper;

    public DepartmentDTO convertToDepartmentDTO(DepartmentEntity departmentEntity) {
        if (departmentEntity == null) {
            return null;
        }
        return modelMapper.map(departmentEntity, DepartmentDTO.class);
    }

    public List<DepartmentDTO> convertToDepartmentDTOList(List<DepartmentEntity> departmentEntityList) {
        List<DepartmentDTO> departmentDTOList = new ArrayList<>();
        for (DepartmentEntity departmentEntity : departmentEntityList) {
            DepartmentDTO departmentDTO = modelMapper.map(departmentEntity, DepartmentDTO.class);
            departmentDTOList.add(departmentDTO);
        }
        return departmentDTOList;
    }
}
