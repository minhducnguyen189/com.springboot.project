package com.springboot.project.functional.programming.app.service;

import com.springboot.project.functional.programming.app.model.CustomerDetails;
import com.springboot.project.functional.programming.app.model.CustomerQueryParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CustomerFacade {

    @Autowired
    @Qualifier("customerIdentityService")
    private BiConsumer<CustomerQueryParams, CustomerDetails> customerIdentityService;
    @Autowired
    @Qualifier("customerLocationService")
    private BiConsumer<CustomerQueryParams, CustomerDetails> customerLocationService;

    @Autowired
    @Qualifier("customerDetailService")
    private BiConsumer<CustomerQueryParams, CustomerDetails> customerDetailService;

    public CustomerDetails getCustomerDetails(CustomerQueryParams customerQueryParams) {
        CustomerDetails customerDetails = new CustomerDetails();
        this.customerIdentityService
                .andThen(customerDetailService)
                .andThen(customerLocationService)
                .accept(customerQueryParams, customerDetails);
        return customerDetails;
    }

}
