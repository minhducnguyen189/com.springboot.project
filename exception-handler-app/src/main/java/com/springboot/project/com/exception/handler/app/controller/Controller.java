package com.springboot.project.com.exception.handler.app.controller;

import com.springboot.project.com.exception.handler.app.model.CustomerRequest;
import com.springboot.project.com.exception.handler.app.model.CustomerResponse;
import com.springboot.project.com.exception.handler.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class Controller {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/customers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UUID> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return new ResponseEntity<>(customerService.createCustomer(customerRequest), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> getCustomerByEmail(@RequestParam(name = "email") String email ) {
        return new ResponseEntity<>(customerService.getCustomerByEmail(email), HttpStatus.OK);
    }

}
