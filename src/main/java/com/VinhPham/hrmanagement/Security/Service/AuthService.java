package com.VinhPham.hrmanagement.Security.Service;

import com.VinhPham.hrmanagement.CustomException.UserAlreadyExistsException;
import com.VinhPham.hrmanagement.DTO.AccountUpdateDTO;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.SignInRequestDTO;
import com.VinhPham.hrmanagement.DTO.SignUpRequestDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface AuthService {
    ResponseEntity<ApiResponseDTO<?>> signUp(SignUpRequestDTO signUpRequest);
    ResponseEntity<ApiResponseDTO<?>> signIn(SignInRequestDTO signInRequest);
    String createPassword(LocalDate birthday);
    String createUsername(String name);
    String createEmail(String name, Long id);
}
