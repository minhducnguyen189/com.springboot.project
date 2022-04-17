package com.springboot.project.swagger.app.controller;

import com.springboot.project.swagger.app.api.V1Api;
import com.springboot.project.swagger.app.model.Customer;
import com.springboot.project.swagger.app.model.CustomerRequest;
import com.springboot.project.swagger.app.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ServerController implements V1Api {


    private final CustomerService customerService;


    @Override
    public ResponseEntity<UUID> createCustomer(CustomerRequest customerRequest) {
        return new ResponseEntity<>(this.customerService.createCustomer(customerRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(this.customerService.getCustomers(), HttpStatus.OK);
    }

}
