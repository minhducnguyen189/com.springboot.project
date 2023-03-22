package com.springboot.project.json.schema.validator.controller;

import com.springboot.project.json.schema.validator.model.Customer;
import com.springboot.project.json.schema.validator.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public ResponseEntity<Customer> createCustomer(String customerJson) {
        return new ResponseEntity<>(this.customerService.createCustomer(customerJson), HttpStatus.CREATED);
    }

}
