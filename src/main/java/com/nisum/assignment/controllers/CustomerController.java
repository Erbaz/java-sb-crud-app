package com.nisum.assignment.controllers;

import com.nisum.assignment.dtos.CreateCustomerRequestDto;
import com.nisum.assignment.dtos.CreateCustomerResponseDto;
import com.nisum.assignment.entities.CustomerEntity;
import com.nisum.assignment.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CreateCustomerResponseDto> createCustomer(@Validated @RequestBody CreateCustomerRequestDto customer) {
        CreateCustomerResponseDto response = customerService.createCustomer(customer);
        return ResponseEntity.ok(response);
    }
}
