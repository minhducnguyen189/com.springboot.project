package com.springboot.project.openapi.app.service;


import com.springboot.cloud.openfeign.openapi.server.app.model.Customer;
import com.springboot.cloud.openfeign.openapi.server.app.model.CustomerRequest;
import com.springboot.project.openapi.app.entity.CustomerEntity;
import com.springboot.project.openapi.app.entity.Gender;
import com.springboot.project.openapi.app.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {

    private final CustomerRepository customerRepository;


    public UUID createCustomer(CustomerRequest customerRequest) {
        CustomerEntity customerEntity = this.toCustomerEntity(new CustomerEntity(), customerRequest);
        return this.customerRepository.save(customerEntity).getId();
    }

    public List<Customer> getCustomers() {
        List<CustomerEntity> customerEntities = this.customerRepository.findAll();
        return this.toCustomers(customerEntities);
    }

    private CustomerEntity toCustomerEntity(CustomerEntity customerEntity, CustomerRequest customerRequest) {
        customerEntity.setAddress(customerRequest.getAddress());
        customerEntity.setDob(customerRequest.getDob());
        customerEntity.setEmail(customerRequest.getEmail());
        customerEntity.setGender(Gender.toGender(customerRequest.getGender().getValue()));
        customerEntity.setFullName(customerRequest.getFullName());
        customerEntity.setPhone(customerRequest.getPhone());
        return customerEntity;
    }

    private List<Customer> toCustomers(List<CustomerEntity> customerEntities) {
        return customerEntities.stream().map(c -> this.toCustomer(c, new Customer())).collect(Collectors.toList());
    }

    private Customer toCustomer(CustomerEntity customerEntity, Customer customer) {
        customer.setId(customerEntity.getId());
        customer.setAddress(customerEntity.getAddress());
        customer.setFullName(customerEntity.getFullName());
        customer.setEmail(customerEntity.getEmail());
        customer.setGender(Customer.GenderEnum.fromValue(customerEntity.getGender().name()));
        customer.setPhone(customerEntity.getPhone());
        customer.setDob(customerEntity.getDob());
        customer.setCreatedAt(customerEntity.getCreatedAt());
        customer.setUpdatedAt(customerEntity.getUpdatedAt());
        return customer;
    }

}


