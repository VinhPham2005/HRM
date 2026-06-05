package com.VinhPham.hrmanagement.Converter;

import com.VinhPham.hrmanagement.DTO.InsuranceDTO;
import com.VinhPham.hrmanagement.DTO.LeaveManagementDTO;
import com.VinhPham.hrmanagement.Entity.InsuranceEntity;
import com.VinhPham.hrmanagement.Entity.LeaveManagementEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LeaveManagementConverter {
    @Autowired
    ModelMapper modelMapper;

    public LeaveManagementDTO convertToLeaveManagementDTO(LeaveManagementEntity leaveManagementEntity) {
        if (leaveManagementEntity == null) {
            return null;
        }
        return modelMapper.map(leaveManagementEntity, LeaveManagementDTO.class);
    }

    public List<LeaveManagementDTO> convertToLeaveManagementDTOList (List<LeaveManagementEntity> leaveManagementEntityList) {
        List<LeaveManagementDTO> leaveManagementDTOList = new ArrayList<>();
        for (LeaveManagementEntity leaveManagementEntity : leaveManagementEntityList) {
            LeaveManagementDTO leaveManagementDTO = modelMapper.map(leaveManagementEntity, LeaveManagementDTO.class);
            leaveManagementDTOList.add(leaveManagementDTO);
        }
        return leaveManagementDTOList;
    }
}
