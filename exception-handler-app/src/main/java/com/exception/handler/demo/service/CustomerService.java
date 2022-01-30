package com.exception.handler.demo.service;

import com.exception.handler.demo.entity.CustomerEntity;
import com.exception.handler.demo.exception.ResourceNotFoundException;
import com.exception.handler.demo.model.CustomerRequest;
import com.exception.handler.demo.model.CustomerResponse;
import com.exception.handler.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public UUID createCustomer(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = this.toCustomerEntity(customerRequest);
        return this.customerRepository.save(customerEntity).getId();
    }

    public CustomerResponse getCustomerByEmail(String email) {
        CustomerEntity customerEntity = this.customerRepository.findCustomerByEmail(email);
        if (Objects.isNull(customerEntity)) {
            throw new ResourceNotFoundException("service.customer.email.1", email);
        }
        return this.toCustomerResponse(customerEntity);
    }

    private CustomerEntity toCustomerEntity(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setAddress(customerRequest.getAddress());
        customerEntity.setDob(customerRequest.getDob());
        customerEntity.setEmail(customerRequest.getEmail());
        customerEntity.setGender(customerRequest.getGender());
        customerEntity.setFullName(customerRequest.getFullName());
        customerEntity.setPhone(customerRequest.getPhone());
        return customerEntity;
    }

    private CustomerResponse toCustomerResponse(CustomerEntity customerEntity) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customerEntity.getId());
        customerResponse.setAddress(customerEntity.getAddress());
        customerResponse.setDob(customerEntity.getDob());
        customerResponse.setFullName(customerEntity.getFullName());
        customerResponse.setPhone(customerEntity.getPhone());
        customerResponse.setGender(customerEntity.getGender());
        customerResponse.setEmail(customerEntity.getEmail());
        return customerResponse;
    }

}
