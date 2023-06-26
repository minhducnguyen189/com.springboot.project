package com.springboot.project.drools.spring.app.repository;

import com.springboot.project.drools.spring.app.entity.BusinessRule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusinessRuleRepository extends MongoRepository<BusinessRule, UUID> {

}
