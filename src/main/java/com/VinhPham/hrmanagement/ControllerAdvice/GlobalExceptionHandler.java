package com.VinhPham.hrmanagement.ControllerAdvice;

import com.VinhPham.hrmanagement.CustomException.EmailAlreadyExistsException;
import com.VinhPham.hrmanagement.CustomException.UserAlreadyExistsException;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<?>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {

        List<String> errorMessage = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.add(error.getDefaultMessage());
        });
        return ResponseEntity
                .badRequest()
                .body(
                        ApiResponseDTO.builder()
                                .status("FAIL")
                                .message("Registration Failed: Please provide valid data.")
                                .response(errorMessage)
                                .build()
                );
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDTO<?>> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ApiResponseDTO.builder()
                                .status("FAIL")
                                .message("Registration Failed: User with the same username already exists.")
                                .response(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDTO<?>> EmailAlreadyExistsExceptionHandler(EmailAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ApiResponseDTO.builder()
                                .status("FAIL")
                                .message("Registration Failed: User with the same email already exists.")
                                .response(exception.getMessage())
                                .build()
                );
    }
}
