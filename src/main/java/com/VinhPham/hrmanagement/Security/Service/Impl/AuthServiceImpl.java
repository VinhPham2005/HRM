package com.VinhPham.hrmanagement.Security.Service.Impl;

import com.VinhPham.hrmanagement.DTO.*;
import com.VinhPham.hrmanagement.Entity.ContractEntity;
import com.VinhPham.hrmanagement.Entity.EmployeeAccountEntity;
import com.VinhPham.hrmanagement.Entity.EmployeeEntity;
import com.VinhPham.hrmanagement.Security.JWT.JwtUtils;
import com.VinhPham.hrmanagement.Security.Service.AuthService;
import com.VinhPham.hrmanagement.Service.ContractService;
import com.VinhPham.hrmanagement.Service.EmployeeAccountService;
import com.VinhPham.hrmanagement.Service.EmployeeService;
import com.VinhPham.hrmanagement.Utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    EmployeeAccountService employeeAccountService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ContractService contractService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ApiResponseDTO<?>> signUp(SignUpRequestDTO signUpRequest) {
        EmployeeCreateDTO empReq = EmployeeCreateDTO.builder()
                .name(signUpRequest.getName())
                .birthday(signUpRequest.getBirthday())
                .gender(signUpRequest.getGender())
                .address(signUpRequest.getAddress())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .build();

        EmployeeEntity employee = employeeService.createEmployee(empReq);
        employeeService.save(employee);

        ContractCreateDTO contractReq = ContractCreateDTO.builder()
                .startDate(signUpRequest.getStartDate())
                .endDate(signUpRequest.getEndDate())
                .basicSalary(signUpRequest.getBasicSalary())
                .build();

        ContractEntity contractEntity = contractService.createContract(contractReq);
        contractEntity.setEmployee(employee);
        contractService.save(contractEntity);

        String email = createEmail(employee.getName(), employee.getId());
        String username = createUsername(employee.getName());
        String password = createPassword(employee.getBirthday());

        EmployeeAccountCreateDTO empAccReq = EmployeeAccountCreateDTO.builder()
                .email(email)
                .username(username)
                .password(password)
                .build();

        EmployeeAccountEntity account = employeeAccountService.createEmployeeAccount(empAccReq);
        account.setEmployee(employee);
        employeeAccountService.save(account);


        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponseDTO.builder()
                        .status("SUCCESS")
                        .message("User account has been successfully created!")
                        .build()
        );
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> signIn(SignInRequestDTO signInRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            SignInResponseDTO signInResponse = SignInResponseDTO.builder()
                    .token(jwt)
                    .username(userDetails.getUsername())
                    .type("Bearer")
                    .roles(roles)
                    .build();

            return ResponseEntity.ok(
                    ApiResponseDTO.builder()
                            .status("SUCCESS")
                            .message("User signed in successfully!")
                            .response(signInResponse)
                            .build()
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    ApiResponseDTO.builder()
                            .status("FAIL")
                            .message("Invalid username or password")
                            .response(e.getMessage())
                            .build()
            );
        }
    }

    @Override
    public String createEmail(String name, Long id) {
        name = StringUtils.removeAccent(name).toLowerCase().trim();
        List<String> parts = List.of(name.split("\\s+"));

        String firstName = parts.get(parts.size() - 1);

        String init = "";
        if (parts.size() > 1) {
            StringBuilder initials = new StringBuilder();
            for (int i = 0; i < parts.size() - 1; i++) {
                initials.append(parts.get(i).charAt(0));
            }
            init = initials.toString();
        }

        String base = firstName.substring(0,1).toUpperCase() + firstName.substring(1) + init.toUpperCase() + "NV";
        String domain = "@company.com";

        return base + id + domain;
    }

    @Override
    public String createPassword(LocalDate birthday) {
        return birthday.format(DateTimeFormatter.ofPattern("ddMMyyyy"));
    }

    @Override
    public String createUsername(String name) {
        name = StringUtils.removeAccent(name);
        return name.toLowerCase().replaceAll("\\s+", "");
    }
}
