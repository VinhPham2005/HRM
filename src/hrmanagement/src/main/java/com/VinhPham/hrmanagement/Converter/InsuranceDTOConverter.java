package com.VinhPham.hrmanagement.Converter;

import com.VinhPham.hrmanagement.DTO.InsuranceDTO;
import com.VinhPham.hrmanagement.Entity.InsuranceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InsuranceDTOConverter {
    @Autowired
    ModelMapper modelMapper;

    public InsuranceDTO convertToInsuranceDTO(InsuranceEntity insuranceEntity) {
        if (insuranceEntity == null) {
            return null;
        }
        return modelMapper.map(insuranceEntity, InsuranceDTO.class);
    }

    public List<InsuranceDTO> convertToInsuranceDTOList(List<InsuranceEntity> insuranceEntityList) {
        List<InsuranceDTO> insuranceDTOList = new ArrayList<>();
        for (InsuranceEntity insuranceEntity : insuranceEntityList) {
            InsuranceDTO insuranceDTO = modelMapper.map(insuranceEntity, InsuranceDTO.class);
            insuranceDTOList.add(insuranceDTO);
        }
        return insuranceDTOList;
    }
}
