package com.springboot.project.json.schema.validator.repository;

import com.springboot.project.json.schema.validator.model.JsonSchemaValidator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JsonSchemaRepository extends MongoRepository<JsonSchemaValidator, Long> {

    List<JsonSchemaValidator> findJsonSchemaValidatorByName(String name);

    JsonSchemaValidator findFirstByOrderByVersionDesc(String name);

}
