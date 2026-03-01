package com.nisum.assignment.controllers;

import com.nisum.assignment.dtos.*;
import com.nisum.assignment.entities.CustomerEntity;
import com.nisum.assignment.services.CustomerService;
import com.nisum.assignment.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<AuthResponseDto<CreateCustomerResponseDto>> createCustomer(@Validated @RequestBody CreateCustomerRequestDto customer) {
        CreateCustomerResponseDto response = customerService.createCustomer(customer);
        AuthResponseDto<CreateCustomerResponseDto> authResponse = new AuthResponseDto<>();
        authResponse.data = response;
        authResponse.token = jwtService.generateToken(response.getName(), "USER");
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto<CustomerDto>> loginCustomer(
            @Validated @RequestBody CustomerLoginRequestDto loginRequest){
        CustomerDto response = customerService.loginCustomer(loginRequest);
        AuthResponseDto<CustomerDto> authResponse = new AuthResponseDto<>();
        authResponse.data = response;
        authResponse.token = jwtService.generateToken(response.getName(), "USER");
        return ResponseEntity.ok(authResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID id){
        CustomerDto response = customerService.findCustomerById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable UUID id, @Validated @RequestBody UpdateCustomerRequestDto customer){
        CustomerDto response = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    };
}
