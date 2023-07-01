package com.springboot.project.drools.spring.app.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.descr.*;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

@Service
public class DroolsService {

    public StatelessKieSession createStatelessKieSession(String drlString, String targetPath) {
        KieHelper kieHelper = new KieHelper();
        Resource drlResource = ResourceFactory.newByteArrayResource(drlString.getBytes());
        drlResource.setTargetPath(targetPath);
        kieHelper.addResource(drlResource, ResourceType.DRL);
        return kieHelper.build().newStatelessKieSession();
    }

     public String createDrlFileFromJsonNode(JsonNode jsonNode) {
         jsonNode = jsonNode.isArray() ? jsonNode.get(0) : jsonNode;
         PackageDescr packageDescr = new PackageDescr();
         packageDescr.setName(jsonNode.get("name").asText());
         JsonNode imports = jsonNode.get("imports");
         for (JsonNode importItem: imports) {
             ImportDescr importDescr = new ImportDescr();
             importDescr.setTarget(importItem.asText());
             packageDescr.addImport(importDescr);
         }
         JsonNode globals = jsonNode.get("globals");
         for (JsonNode global: globals) {
             GlobalDescr globalDescr = new GlobalDescr();
             globalDescr.setType(global.get("type").asText());
             globalDescr.setIdentifier(global.get("identifier").asText());
             packageDescr.addGlobal(globalDescr);
         }
         JsonNode rules = jsonNode.get("rules");
         for (JsonNode rule: rules) {
             RuleDescr ruleDescr = new RuleDescr();
             ruleDescr.setName(rule.get("rule").asText());
             JsonNode whenNodes = rule.get("when");
             AndDescr andDescr = new AndDescr();
             for (JsonNode whenNode : whenNodes) {
                 String identifier = whenNode.get("identifier").asText();
                 String type = whenNode.get("type").asText();
                 PatternDescr patternDescr = new PatternDescr();
                 patternDescr.setIdentifier(identifier);
                 patternDescr.setObjectType(type);
                 JsonNode conditionStatements = whenNode.get("conditions");
                 for (JsonNode condition : conditionStatements) {
                     ExprConstraintDescr exprConstraintDescr = new ExprConstraintDescr();
                     exprConstraintDescr.setText(condition.asText());
                     patternDescr.addConstraint(exprConstraintDescr);
                 }
                 andDescr.addDescr(patternDescr);
             }
             ruleDescr.setLhs(andDescr);
             JsonNode thenNodes = rule.get("then");
             StringBuilder stringBuilder = new StringBuilder();
             for (JsonNode thenNode : thenNodes) {
                 JsonNode actionNodes = thenNode.get("actions");
                 for (JsonNode actionNode : actionNodes) {
                     stringBuilder.append(actionNode.asText());
                     stringBuilder.append(";");
                 }
             }
             ruleDescr.setConsequence(stringBuilder.toString());
             packageDescr.addRule(ruleDescr);
         }
         return new DrlDumper().dump(packageDescr);
     }

}
