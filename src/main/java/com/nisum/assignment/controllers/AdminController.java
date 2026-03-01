package com.nisum.assignment.controllers;

import com.nisum.assignment.dtos.AdminLoginRequestDto;
import com.nisum.assignment.dtos.AuthResponseDto;
import com.nisum.assignment.services.JwtService;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private JwtService jwtService;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto<Null>> login(@RequestBody @Validated AdminLoginRequestDto request) {

        if (!request.getUsername().equals(adminUsername) ||
                !request.getPassword().equals(adminPassword)) {
            throw new RuntimeException("Invalid admin credentials");
        }

        AuthResponseDto<Null> response = new AuthResponseDto<>();
        response.token = jwtService.generateToken(adminUsername, "ADMIN");
        response.data = null;

        return ResponseEntity.ok(response);
    }
}