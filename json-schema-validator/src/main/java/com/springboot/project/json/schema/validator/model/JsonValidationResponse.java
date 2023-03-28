package com.springboot.project.json.schema.validator.model;

import org.bson.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JsonValidationResponse {
    
    private boolean validJson;
    private Long schemaVersion;
    private Document jsonInput;

}
