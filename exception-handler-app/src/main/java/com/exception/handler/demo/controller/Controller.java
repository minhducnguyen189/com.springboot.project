package com.exception.handler.demo.controller;

import com.exception.handler.demo.model.CustomerRequest;
import com.exception.handler.demo.model.CustomerResponse;
import com.exception.handler.demo.service.CustomerService;
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
