package com.springboot.project.functional.programming.app.service;

import com.springboot.project.functional.programming.app.model.CustomerDetails;
import com.springboot.project.functional.programming.app.model.CustomerQueryParams;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CustomerLocationService implements BiConsumer<CustomerQueryParams, CustomerDetails> {

    @Override
    public void accept(CustomerQueryParams customerQueryParams, CustomerDetails customerDetails) {
        customerDetails.setAddress("abcxyz");
    }
}
