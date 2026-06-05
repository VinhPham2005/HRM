package com.VinhPham.hrmanagement.Service.Impl;

import com.VinhPham.hrmanagement.DTO.ContractCreateDTO;
import com.VinhPham.hrmanagement.Entity.ContractEntity;
import com.VinhPham.hrmanagement.Repository.ContractRepository;
import com.VinhPham.hrmanagement.Service.ContractService;
import com.VinhPham.hrmanagement.Utils.DateFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    ContractRepository contractRepository;

    @Override
    public ContractEntity createContract(ContractCreateDTO contractCreateDTO) {
        return ContractEntity.builder()
                .startDate(DateFormatter.normalizeDate(contractCreateDTO.getStartDate()))
                .endDate(DateFormatter.normalizeDate(contractCreateDTO.getEndDate()))
                .basicSalary(contractCreateDTO.getBasicSalary())
                .build();
    }

    @Override
    public void save(ContractEntity contractEntity) {
        contractRepository.save(contractEntity);
    }
}
