package com.VinhPham.hrmanagement.Service;

import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.ContractCreateDTO;
import com.VinhPham.hrmanagement.Entity.ContractEntity;
import org.springframework.http.ResponseEntity;

public interface ContractService {
    ContractEntity createContract(ContractCreateDTO contractCreateDTO);
    void save(ContractEntity contractEntity);
}
