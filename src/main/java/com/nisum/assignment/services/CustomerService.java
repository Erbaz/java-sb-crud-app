package com.nisum.assignment.services;

import com.nisum.assignment.dtos.*;
import com.nisum.assignment.entities.CustomerEntity;
import com.nisum.assignment.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CreateCustomerResponseDto createCustomer(CreateCustomerRequestDto customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customer.name);
        customerEntity.setAddress(customer.address);
        customerEntity.setEmail(customer.email);
        String hashedPassword = passwordEncoder.encode(customer.password);
        customerEntity.setPassword(hashedPassword);
        CustomerEntity newCustomer = customerRepository.save(customerEntity);
        CreateCustomerResponseDto newCustomerResponse = new CreateCustomerResponseDto();
        newCustomerResponse.id = String.valueOf(newCustomer.getId());
        newCustomerResponse.name = newCustomer.getName();
        newCustomerResponse.address = newCustomer.getAddress();
        newCustomerResponse.email = newCustomer.getEmail();
        return newCustomerResponse;
    }

    public CustomerDto loginCustomer(CustomerLoginRequestDto loginRequest) {
        CustomerEntity customerEntity = customerRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new RuntimeException("Customer not found"));
        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                customerEntity.getPassword())) {

            throw new RuntimeException("Invalid credentials");
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.id = String.valueOf(customerEntity.getId());
        customerDto.name = customerEntity.getName();
        customerDto.email = customerEntity.getEmail();
        customerDto.address = customerEntity.getAddress();
        customerDto.orders = customerEntity.getOrders();
        return customerDto;
    }

    public CustomerDto findCustomerById(UUID id){
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        CustomerDto customerDto = new CustomerDto();
        customerDto.id = String.valueOf(customerEntity.getId());
        customerDto.name = customerEntity.getName();
        customerDto.address = customerEntity.getAddress();
        customerDto.orders = customerEntity.getOrders();
        return customerDto;
    }

    public CustomerDto updateCustomer(UUID id, UpdateCustomerRequestDto customer) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        if(customer.name != null){
            customerEntity.setName(customer.name);
        }
        if(customer.address != null){
            customerEntity.setAddress(customer.address);
        }
        CustomerEntity updatedCustomer = customerRepository.save(customerEntity);
        CustomerDto updatedCustomerDto = new CustomerDto();
        updatedCustomerDto.id = String.valueOf(updatedCustomer.getId());
        updatedCustomerDto.name = updatedCustomer.getName();
        updatedCustomerDto.address = updatedCustomer.getAddress();
        updatedCustomerDto.orders = updatedCustomer.getOrders();
        return updatedCustomerDto;
    }

    public void deleteCustomer(UUID id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customerRepository.delete(customerEntity);
    }

}
