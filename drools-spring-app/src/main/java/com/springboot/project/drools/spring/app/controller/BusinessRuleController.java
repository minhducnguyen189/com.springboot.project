package com.springboot.project.drools.spring.app.controller;

import com.springboot.project.drools.spring.app.entity.BusinessRule;
import com.springboot.project.drools.spring.app.service.BusinessRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusinessRuleController {

    private final BusinessRuleService businessRuleService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/drools/business-rules", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusinessRule> createBusinessRule(@RequestBody String businessRuleString) {
        return new ResponseEntity<>(this.businessRuleService.createBusinessRule(businessRuleString), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/drools/business-rules", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BusinessRule>> getBusinessRules() {
        return new ResponseEntity<>(this.businessRuleService.getBusinessRules(), HttpStatus.OK);
    }

}
