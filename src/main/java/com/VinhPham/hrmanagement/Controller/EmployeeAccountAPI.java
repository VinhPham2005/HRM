package com.VinhPham.hrmanagement.Controller;

import com.VinhPham.hrmanagement.CustomException.UserAlreadyExistsException;
import com.VinhPham.hrmanagement.DTO.AccountUpdateDTO;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.SignInRequestDTO;
import com.VinhPham.hrmanagement.DTO.SignUpRequestDTO;
import com.VinhPham.hrmanagement.Security.Service.AuthService;
import com.VinhPham.hrmanagement.Service.EmployeeAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class EmployeeAccountAPI {
    @Autowired
    private EmployeeAccountService employeeAccountService;

    @PutMapping("/change-password")
    public ResponseEntity<ApiResponseDTO<?>> changePassword(@RequestBody @Valid AccountUpdateDTO accountUpdateDTO) {
        return employeeAccountService.changePassword(accountUpdateDTO);
    }

    @PutMapping("/change-username")
    public ResponseEntity<ApiResponseDTO<?>> changeUsername(@RequestBody @Valid AccountUpdateDTO accountUpdateDTO) {
        return employeeAccountService.changeUsername(accountUpdateDTO);
    }


}
