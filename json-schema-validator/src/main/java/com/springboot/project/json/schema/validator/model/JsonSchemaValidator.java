package com.springboot.project.json.schema.validator.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.util.UUID;

import javax.persistence.Id;

@Getter
@Setter
@org.springframework.data.mongodb.core.mapping.Document("jsonSchemas")
public class JsonSchemaValidator {

    @Id
    private UUID id;
    private String name;
    private Long version;
    private String status;
    private Document value;

}
