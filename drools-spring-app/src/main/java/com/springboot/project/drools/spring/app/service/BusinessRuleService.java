package com.springboot.project.drools.spring.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.drools.spring.app.entity.BusinessRule;
import com.springboot.project.drools.spring.app.repository.BusinessRuleRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusinessRuleService {

    private final ObjectMapper objectMapper;
    private final BusinessRuleRepository businessRuleRepository;

    public BusinessRule createBusinessRule(String businessRuleJson) {
        BusinessRule businessRule = new BusinessRule();
        Document document = Document.parse(businessRuleJson);
        String ruleName = document.getString("name");
        businessRule.setId(UUID.randomUUID());
        businessRule.setName(ruleName);
        businessRule.setValue(document);
        return this.businessRuleRepository.save(businessRule);
    }

}
