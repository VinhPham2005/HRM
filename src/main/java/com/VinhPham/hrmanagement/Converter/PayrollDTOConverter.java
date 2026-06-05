package com.VinhPham.hrmanagement.Converter;

import com.VinhPham.hrmanagement.DTO.InsuranceDTO;
import com.VinhPham.hrmanagement.DTO.PayrollDTO;
import com.VinhPham.hrmanagement.Entity.InsuranceEntity;
import com.VinhPham.hrmanagement.Entity.PayrollEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PayrollDTOConverter {
    @Autowired
    ModelMapper modelMapper;

    public PayrollDTO convertToPayrollDTO(PayrollEntity payrollEntity) {
        if (payrollEntity == null) {
            return null;
        }
        return modelMapper.map(payrollEntity, PayrollDTO.class);
    }

    public List<PayrollDTO> convertToPayrollDTOList(List<PayrollEntity> payrollEntityList) {
        List<PayrollDTO> payrollDTOList = new ArrayList<>();
        for (PayrollEntity payrollEntity : payrollEntityList) {
            PayrollDTO payrollDTO = modelMapper.map(payrollEntity, PayrollDTO.class);
            payrollDTOList.add(payrollDTO);
        }
        return payrollDTOList;
    }
}
