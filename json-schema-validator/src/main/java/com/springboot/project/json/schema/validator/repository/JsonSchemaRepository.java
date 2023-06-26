package com.springboot.project.json.schema.validator.repository;

import com.springboot.project.json.schema.validator.model.JsonSchemaValidator;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JsonSchemaRepository extends MongoRepository<JsonSchemaValidator, UUID> {

    List<JsonSchemaValidator> findJsonSchemaValidatorByName(String name);

    List<JsonSchemaValidator> findByOrderByVersionDesc(String name);

}
