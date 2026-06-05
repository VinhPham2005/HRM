package com.VinhPham.hrmanagement.Controller;

import com.VinhPham.hrmanagement.CustomException.UserAlreadyExistsException;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.SignInRequestDTO;
import com.VinhPham.hrmanagement.DTO.SignUpRequestDTO;
import com.VinhPham.hrmanagement.Security.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthAPI {
    @Autowired
    private AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponseDTO<?>> authenticateUser(@RequestBody @Valid SignInRequestDTO signInRequestDto) {
        return authService.signIn(signInRequestDto);
    }
}
