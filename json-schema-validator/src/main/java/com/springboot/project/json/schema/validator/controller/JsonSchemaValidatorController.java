package com.springboot.project.json.schema.validator.controller;

import com.springboot.project.json.schema.validator.model.JsonSchemaValidator;
import com.springboot.project.json.schema.validator.service.JsonSchemaValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonSchemaValidatorController {

    @Autowired
    private JsonSchemaValidatorService jsonSchemaValidatorService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/json/validator/schemas")
    public ResponseEntity<JsonSchemaValidator> createCustomer(@RequestBody String jsonSchema) {
        return new ResponseEntity<>(this.jsonSchemaValidatorService.createJsonSchemaValidator(jsonSchema), HttpStatus.CREATED);
    }

}
