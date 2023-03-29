package com.springboot.project.json.schema.validator.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;


import java.util.UUID;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@org.springframework.data.mongodb.core.mapping.Document("json_schemas")
public class JsonSchemaValidator {

    @Id
    @NotNull
    private UUID id;
    @NotNull(message = "name of schema can't be null")
    private String name;
    private Long version;
    private String status;
    @NotNull(message = "value of schema can't be null")
    private Document value;

}
