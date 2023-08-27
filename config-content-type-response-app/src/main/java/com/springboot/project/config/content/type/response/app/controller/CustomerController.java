package com.springboot.project.config.content.type.response.app.controller;

import com.springboot.project.config.content.type.response.app.model.CustomerRequest;
import com.springboot.project.config.content.type.response.app.model.CustomerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class CustomerController {

    private List<CustomerResponse> customers = new ArrayList<>();

    @RequestMapping(method = RequestMethod.POST, path = "/v1/customers", consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = this.toCustomerResponse(customerRequest);
        customerResponse.setId(UUID.randomUUID());
        this.customers.add(customerResponse);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/customers"
            ,produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<CustomerResponse>> getCustomers() {
        return new ResponseEntity<>(this.customers, HttpStatus.OK);
    }

    private CustomerResponse toCustomerResponse(CustomerRequest customerRequest) {
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setAddress(customerRequest.getAddress());
        customerResponse.setDob(customerRequest.getDob());
        customerResponse.setFullName(customerRequest.getFullName());
        customerResponse.setPhone(customerRequest.getPhone());
        customerResponse.setGender(customerRequest.getGender());
        customerResponse.setEmail(customerRequest.getEmail());
        return customerResponse;
    }


}
