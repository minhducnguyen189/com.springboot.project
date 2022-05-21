package com.springboot.project.functional.programming.app.controller;

import com.springboot.project.functional.programming.app.model.CustomerDetails;
import com.springboot.project.functional.programming.app.model.CustomerQueryParams;
import com.springboot.project.functional.programming.app.service.CustomerFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private CustomerFacade customerFacade;


    @RequestMapping(method = RequestMethod.POST, path = "/v1/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CustomerDetails> getCustomerDetails(@RequestBody CustomerQueryParams customerQueryParams) {
        return new ResponseEntity<>(customerFacade.getCustomerDetails(customerQueryParams), HttpStatus.OK);
    }


}
