package com.springboot.project.json.schema.validator.service;

import com.springboot.project.json.schema.validator.model.JsonSchemaValidator;
import com.springboot.project.json.schema.validator.repository.JsonSchemaRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Service
public class JsonSchemaValidatorService {

    @Autowired
    private JsonSchemaRepository jsonSchemaRepository;

    public JsonSchemaValidator createJsonSchemaValidator(String json) {
        JsonSchemaValidator jsonSchemaValidator = new JsonSchemaValidator();
        Document data = Document.parse(json);
        String schemaName = data.getString("name");
        List<JsonSchemaValidator> existedJsonSchemas = this.getJsonSchemaValidatorByName(schemaName);
        if (CollectionUtils.isEmpty(existedJsonSchemas)) {
            jsonSchemaValidator.setVersion(1L);
            return this.saveNewJsonSchemaValidator(jsonSchemaValidator, data);
        }
        JsonSchemaValidator latestJsonSchema = existedJsonSchemas.get(0);
        latestJsonSchema.setStatus("inactive");
        this.jsonSchemaRepository.save(latestJsonSchema);
        jsonSchemaValidator.setVersion(latestJsonSchema.getVersion() + 1);
        this.saveNewJsonSchemaValidator(jsonSchemaValidator, data);
        return this.jsonSchemaRepository.save(jsonSchemaValidator);
    }

    public List<JsonSchemaValidator> getJsonSchemaValidatorByName(String name) {
        return this.jsonSchemaRepository.findJsonSchemaValidatorByName(name);
    }

    public JsonSchemaValidator saveNewJsonSchemaValidator(JsonSchemaValidator jsonSchemaValidator, Document data) {
        jsonSchemaValidator.setId(UUID.randomUUID());
        jsonSchemaValidator.setName(data.getString("name"));
        jsonSchemaValidator.setStatus("active");
        jsonSchemaValidator.setVersion(jsonSchemaValidator.getVersion());
        jsonSchemaValidator.setValue(data);
        return this.jsonSchemaRepository.save(jsonSchemaValidator);
    }

}
