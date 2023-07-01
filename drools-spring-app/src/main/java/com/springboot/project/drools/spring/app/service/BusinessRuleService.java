package com.springboot.project.drools.spring.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.drools.spring.app.entity.BusinessRule;
import com.springboot.project.drools.spring.app.model.ResultObject;
import com.springboot.project.drools.spring.app.repository.BusinessRuleRepository;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BusinessRuleService {

    private final ObjectMapper objectMapper;
    private final BusinessRuleRepository businessRuleRepository;
    private final DroolsService droolsService;

    public BusinessRule createBusinessRule(String businessRuleJson) {
        BusinessRule businessRule = new BusinessRule();
        Document document = Document.parse(businessRuleJson);
        String ruleName = document.getString("name");
        businessRule.setId(UUID.randomUUID());
        businessRule.setName(ruleName);
        businessRule.setValue(document);
        return this.businessRuleRepository.save(businessRule);
    }

    public String testInputBusinessRule(String inputData) {
        try {
            JsonNode inputDataJsonNode = this.objectMapper.readTree(inputData);
            JsonNode data = inputDataJsonNode.get("data");
            JsonNode businessRuleJsonNode = inputDataJsonNode.get("businessRule");
            String dumpDrlString = this.droolsService.createDrlFileFromJsonNode(businessRuleJsonNode);
            StatelessKieSession statelessKieSession = this.droolsService.createStatelessKieSession(dumpDrlString, businessRuleJsonNode.get("name").asText());
            ResultObject resultObject = new ResultObject();
            resultObject.setResult(data);
            statelessKieSession.setGlobal("result", resultObject);
            statelessKieSession.execute(resultObject);
            return this.objectMapper.writeValueAsString(statelessKieSession);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
