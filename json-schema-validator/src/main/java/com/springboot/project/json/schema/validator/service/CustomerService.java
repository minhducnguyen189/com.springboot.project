package com.springboot.project.json.schema.validator.service;

import com.springboot.project.json.schema.validator.model.Customer;
import com.springboot.project.json.schema.validator.model.JsonValidationResponse;
import com.springboot.project.json.schema.validator.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final JsonSchemaValidatorService jsonSchemaValidatorService;

    public Customer createCustomer(String customerJson) {
        JsonValidationResponse jsonValidationResponse = this.jsonSchemaValidatorService.validateJsonData("CustomerJsonSchemaValidator", customerJson);
        if (jsonValidationResponse.isValidJson()) {
            Customer customer = new Customer();
            Document data = Document.parse(customerJson);
            customer.setId(UUID.randomUUID());
            customer.setFullName(data.getString("fullName"));
            customer.setEmail(data.getString("email"));
            customer.setData(data);
            return this.customerRepository.save(customer);
        }
        throw new IllegalArgumentException("Customer Json is not Valid: " + customerJson);
    }

}
