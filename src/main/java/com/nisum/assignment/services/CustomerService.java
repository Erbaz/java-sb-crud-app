package com.nisum.assignment.services;

import com.nisum.assignment.dtos.CreateCustomerRequestDto;
import com.nisum.assignment.dtos.CreateCustomerResponseDto;
import com.nisum.assignment.dtos.CustomerDto;
import com.nisum.assignment.dtos.UpdateCustomerRequestDto;
import com.nisum.assignment.entities.CustomerEntity;
import com.nisum.assignment.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CreateCustomerResponseDto createCustomer(CreateCustomerRequestDto customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(customer.name);
        customerEntity.setAddress(customer.address);
        CustomerEntity newCustomer = customerRepository.save(customerEntity);
        CreateCustomerResponseDto newCustomerResponse = new CreateCustomerResponseDto();
        newCustomerResponse.id = String.valueOf(newCustomer.getId());
        newCustomerResponse.name = newCustomer.getName();
        newCustomerResponse.address = newCustomer.getAddress();
        return newCustomerResponse;
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
