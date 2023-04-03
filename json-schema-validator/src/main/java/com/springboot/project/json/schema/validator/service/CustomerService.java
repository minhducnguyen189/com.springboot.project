package com.springboot.project.json.schema.validator.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.json.schema.validator.model.Customer;
import com.springboot.project.json.schema.validator.model.JsonValidationResponse;
import com.springboot.project.json.schema.validator.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final JsonSchemaValidatorService jsonSchemaValidatorService;
    private final ObjectMapper objectMapper;

    public Customer createCustomer(String customerJson) {
        JsonValidationResponse jsonValidationResponse = this.jsonSchemaValidatorService.validateJsonData("CustomerJsonSchemaValidator", customerJson);
        if (jsonValidationResponse.isValidJson()) {
            Customer customer = new Customer();
            JsonNode customerNode;
            try {
                customerNode = this.objectMapper.readTree(customerJson);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Customer Json is not Valid: " + customerJson);
            }
            Document customerData = Document.parse(customerJson);
            customer.setId(UUID.randomUUID());
            customer.setFullName(customerNode.get("customer").get("fullName").asText());
            customer.setEmail(customerNode.get("customer").get("email").asText());
            customer.setData(customerData);
            return this.customerRepository.save(customer);
        }
        throw new IllegalArgumentException("Customer Json is not Valid: " + customerJson);
    }

    public List<Customer> getCustomers() {
       return this.customerRepository.findAll();
    }

}
