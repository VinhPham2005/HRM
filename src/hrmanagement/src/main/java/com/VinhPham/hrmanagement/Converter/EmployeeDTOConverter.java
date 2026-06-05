package com.VinhPham.hrmanagement.Converter;

import com.VinhPham.hrmanagement.DTO.EmployeeDTO;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDTOConverter {
    @Autowired
    ModelMapper modelMapper;

    public EmployeeDTO convertToEmployeeDTO(EmployeeEntity employee) {
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public List<EmployeeDTO> convertToEmployeeDTOList(List<EmployeeEntity> employeeList) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (EmployeeEntity employee : employeeList) {
            EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }
}
