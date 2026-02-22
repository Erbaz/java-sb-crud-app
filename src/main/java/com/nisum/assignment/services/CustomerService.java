package com.nisum.assignment.services;

import com.nisum.assignment.dtos.CreateCustomerRequestDto;
import com.nisum.assignment.dtos.CreateCustomerResponseDto;
import com.nisum.assignment.entities.CustomerEntity;
import com.nisum.assignment.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
