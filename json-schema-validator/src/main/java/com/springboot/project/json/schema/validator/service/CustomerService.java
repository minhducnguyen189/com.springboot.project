package com.springboot.project.json.schema.validator.service;

import com.springboot.project.json.schema.validator.model.Customer;
import com.springboot.project.json.schema.validator.repository.CustomerRepository;

import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(String customerJson) {
        Customer customer = new Customer();
        Document data = Document.parse(customerJson);
        customer.setId(UUID.randomUUID());
        customer.setFullName(data.getString("fullName"));
        customer.setEmail(data.getString("email"));
        customer.setData(data);
        return this.customerRepository.save(customer);
    }

}
