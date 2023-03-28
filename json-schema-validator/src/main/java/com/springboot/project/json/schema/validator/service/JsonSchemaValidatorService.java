package com.springboot.project.json.schema.validator.service;

import com.springboot.project.json.schema.validator.model.JsonSchemaValidator;
import com.springboot.project.json.schema.validator.model.JsonValidationResponse;
import com.springboot.project.json.schema.validator.repository.JsonSchemaRepository;

import lombok.RequiredArgsConstructor;

import org.bson.Document;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JsonSchemaValidatorService {

    private final JsonSchemaRepository jsonSchemaRepository;
    private final CustomDateTimeValidator customDateTimeValidator;

    public JsonSchemaValidator createJsonSchemaValidator(String json) {
        JsonSchemaValidator jsonSchemaValidator = new JsonSchemaValidator();
        Document data = Document.parse(json);
        String schemaName = data.getString("name");
        Document value = data.get("value", Document.class);
        List<JsonSchemaValidator> existedJsonSchemas = this.getJsonSchemaValidatorByName(schemaName);
        if (CollectionUtils.isEmpty(existedJsonSchemas)) {
            jsonSchemaValidator.setVersion(1L);
            return this.saveNewJsonSchemaValidator(jsonSchemaValidator, schemaName, value);
        }
        JsonSchemaValidator latestJsonSchema = this.getlatestJsonSchemaVersion(schemaName);
        latestJsonSchema.setStatus("inactive");
        this.jsonSchemaRepository.save(latestJsonSchema);
        jsonSchemaValidator.setVersion(latestJsonSchema.getVersion() + 1);
        this.saveNewJsonSchemaValidator(jsonSchemaValidator, schemaName, value);
        return this.jsonSchemaRepository.save(jsonSchemaValidator);
    }

    public List<JsonSchemaValidator> getJsonSchemaValidatorByName(String name) {
        return this.jsonSchemaRepository.findJsonSchemaValidatorByName(name);
    }

    public JsonValidationResponse validateJsonData(String schemaName, String jsonData) {
        JsonValidationResponse jsonValidationResponse = new JsonValidationResponse();
        JsonSchemaValidator jsonSchemaValidator = this.getlatestJsonSchemaVersion(schemaName);
        Document jsonSchema = jsonSchemaValidator.getValue();
        boolean isValidJson = this.validateJson(jsonSchema.toJson(), jsonData);
        jsonValidationResponse.setValidJson(isValidJson);
        jsonValidationResponse.setJsonInput(Document.parse(jsonData));
        jsonValidationResponse.setSchemaVersion(jsonSchemaValidator.getVersion());
        return jsonValidationResponse;
    }

    private JsonSchemaValidator saveNewJsonSchemaValidator(JsonSchemaValidator jsonSchemaValidator, String schemaName, Document data) {
        jsonSchemaValidator.setId(UUID.randomUUID());
        jsonSchemaValidator.setName(schemaName);
        jsonSchemaValidator.setStatus("active");
        jsonSchemaValidator.setVersion(jsonSchemaValidator.getVersion());
        jsonSchemaValidator.setValue(data);
        return this.jsonSchemaRepository.save(jsonSchemaValidator);
    }

    private JsonSchemaValidator getlatestJsonSchemaVersion(String schemaName) {
        return this.jsonSchemaRepository.findFirstByOrderByVersionDesc(schemaName);
    }

    private boolean validateJson(String validationSchema, String target) {
        try {
            SchemaLoader loader = SchemaLoader.builder()
                .addFormatValidator(customDateTimeValidator)
                .schemaJson(new JSONObject(validationSchema))
                .draftV7Support()
                .enableOverrideOfBuiltInFormatValidators()
                .build();
            Schema schema = loader.load().build();
            schema.validate(new JSONObject(target));
            return true;
        } catch (ValidationException e) {
            return false;
        }
    }

}
