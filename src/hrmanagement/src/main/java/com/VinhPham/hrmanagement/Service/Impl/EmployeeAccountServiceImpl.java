package com.VinhPham.hrmanagement.Service.Impl;

import com.VinhPham.hrmanagement.DTO.AccountUpdateDTO;
import com.VinhPham.hrmanagement.DTO.ApiResponseDTO;
import com.VinhPham.hrmanagement.DTO.EmployeeAccountCreateDTO;
import com.VinhPham.hrmanagement.ENUM.RoleEnum;
import com.VinhPham.hrmanagement.Entity.EmployeeAccountEntity;
import com.VinhPham.hrmanagement.Repository.EmployeeAccountRepository;
import com.VinhPham.hrmanagement.Service.EmployeeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeAccountServiceImpl implements EmployeeAccountService {
    @Autowired
    EmployeeAccountRepository employeeAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployeeAccountEntity findByUsername(String username) throws UsernameNotFoundException {
        return employeeAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    public EmployeeAccountEntity findByEmail(String email) {
        return employeeAccountRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Override
    public boolean existsByUsername(String username) {
        return employeeAccountRepository.existsByUsername(username);
    }

    @Override
    public void save(EmployeeAccountEntity employeeAccountEntity) {
        employeeAccountRepository.save(employeeAccountEntity);
    }

    @Override
    public EmployeeAccountEntity createEmployeeAccount(EmployeeAccountCreateDTO employeeAccountCreateDTO) {
        return EmployeeAccountEntity.builder()
                .username(employeeAccountCreateDTO.getUsername())
                .password(passwordEncoder.encode(employeeAccountCreateDTO.getPassword()))
                .email(employeeAccountCreateDTO.getEmail())
                .role(RoleEnum.STAFF)
                .enabled(true)
                .build();
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> changePassword(AccountUpdateDTO accountUpdateDTO) {
        EmployeeAccountEntity account = findByEmail(accountUpdateDTO.getEmail());

        if (!passwordEncoder.matches(accountUpdateDTO.getCurPassword(), account.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    ApiResponseDTO.builder()
                            .status("FAIL")
                            .message("Invalid current password")
                            .build()
            );
        }

        if (accountUpdateDTO.getNewPassword() == null || accountUpdateDTO.getNewPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponseDTO.builder()
                            .status("FAIL")
                            .message("New password is required")
                            .build()
            );
        }

        account.setPassword(passwordEncoder.encode(accountUpdateDTO.getNewPassword()));
        save(account);

        return ResponseEntity.ok(
                ApiResponseDTO.builder()
                        .status("SUCCESS")
                        .message("Password changed successfully!")
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> changeUsername(AccountUpdateDTO accountUpdateDTO) {
        EmployeeAccountEntity account = findByEmail(accountUpdateDTO.getEmail());

        if (!passwordEncoder.matches(accountUpdateDTO.getCurPassword(), account.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    ApiResponseDTO.builder()
                            .status("FAIL")
                            .message("Invalid current password")
                            .build()
            );
        }

        if (accountUpdateDTO.getNewUsername() == null || accountUpdateDTO.getNewUsername().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponseDTO.builder()
                            .status("FAIL")
                            .message("New username is required")
                            .build()
            );
        }

        if (existsByUsername(accountUpdateDTO.getNewUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponseDTO.builder()
                            .status("FAIL")
                            .message("Username is already taken")
                            .build()
            );
        }

        account.setUsername(accountUpdateDTO.getNewUsername());
        save(account);

        return ResponseEntity.ok(
                ApiResponseDTO.builder()
                        .status("SUCCESS")
                        .message("Username changed successfully!")
                        .build()
        );
    }
}
