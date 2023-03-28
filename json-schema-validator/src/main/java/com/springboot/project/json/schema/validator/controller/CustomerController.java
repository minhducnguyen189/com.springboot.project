package com.springboot.project.json.schema.validator.controller;

import com.springboot.project.json.schema.validator.model.Customer;
import com.springboot.project.json.schema.validator.service.CustomerService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

    private final CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody String customerJson) {
        return new ResponseEntity<>(this.customerService.createCustomer(customerJson), HttpStatus.CREATED);
    }

}
