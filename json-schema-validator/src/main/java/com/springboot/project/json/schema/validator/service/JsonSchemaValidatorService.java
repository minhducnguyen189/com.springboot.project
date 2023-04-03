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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JsonSchemaValidatorService {

    private final JsonSchemaRepository jsonSchemaRepository;
    private final CustomDateTimeValidator customDateTimeValidator;
    private final SequenceGeneratorService sequenceGeneratorService;

    public JsonSchemaValidator createJsonSchemaValidator(String json) {
        JsonSchemaValidator jsonSchemaValidator = new JsonSchemaValidator();
        Document data = Document.parse(json);
        String schemaName = data.getString("name");
        Document value = data.get("value", Document.class);
        if (Objects.isNull(schemaName) || Objects.isNull(value))
            throw new IllegalArgumentException("schemaName or Value Can't be null");
        Optional<JsonSchemaValidator> latestJsonSchemaOpt = this.getlatestActiveJsonSchemaVersion(schemaName);
        if (!latestJsonSchemaOpt.isPresent()) {
            return this.saveNewJsonSchemaValidator(jsonSchemaValidator, schemaName, value);
        }
        JsonSchemaValidator latestJsonSchema = latestJsonSchemaOpt.get();
        latestJsonSchema.setStatus("inactive");
        this.jsonSchemaRepository.save(latestJsonSchema);
        this.saveNewJsonSchemaValidator(jsonSchemaValidator, schemaName, value);
        return this.jsonSchemaRepository.save(jsonSchemaValidator);
    }

    public List<JsonSchemaValidator> getJsonSchemaValidatorByName(String name) {
        return this.jsonSchemaRepository.findJsonSchemaValidatorByName(name);
    }

    public JsonValidationResponse validateJsonData(String schemaName, String jsonData) {
        Optional<JsonSchemaValidator> latestJsonSchemaOpt = this.getlatestActiveJsonSchemaVersion(schemaName);
        if (!latestJsonSchemaOpt.isPresent()) {
            return this.createJsonValidationResponse(false, jsonData, 0L);
        }
        JsonSchemaValidator latestJsonSchema = latestJsonSchemaOpt.get();
        Document jsonSchema = latestJsonSchema.getValue();
        boolean isValidJson = this.validateJson(jsonSchema.toJson(), jsonData);
        return this.createJsonValidationResponse(isValidJson, jsonData, latestJsonSchema.getVersion());
    }

    private JsonValidationResponse createJsonValidationResponse(boolean isValidJson, String jsonData, Long version) {
        JsonValidationResponse jsonValidationResponse = new JsonValidationResponse();
        jsonValidationResponse.setValidJson(isValidJson);
        jsonValidationResponse.setJsonInput(Document.parse(jsonData));
        jsonValidationResponse.setSchemaVersion(version);
        return jsonValidationResponse;
    }

    private JsonSchemaValidator saveNewJsonSchemaValidator(JsonSchemaValidator jsonSchemaValidator, String schemaName, Document data) {
        jsonSchemaValidator.setId(UUID.randomUUID());
        jsonSchemaValidator.setName(schemaName);
        jsonSchemaValidator.setStatus("active");
        jsonSchemaValidator.setVersion(sequenceGeneratorService.generateVersionSequence(schemaName));
        jsonSchemaValidator.setValue(data);
        return this.jsonSchemaRepository.save(jsonSchemaValidator);
    }

    private Optional<JsonSchemaValidator> getlatestActiveJsonSchemaVersion(String schemaName) {
        List<JsonSchemaValidator> jsonSchemaValidators = this.jsonSchemaRepository.findByOrderByVersionDesc(schemaName);
        for (JsonSchemaValidator jsonSchemaValidator: jsonSchemaValidators) {
            if (jsonSchemaValidator.getStatus().equalsIgnoreCase("active")) {
                return Optional.of(jsonSchemaValidator);
            }
        }
        return Optional.empty();

    }

    private boolean validateJson(String validationSchema, String target) {
        try {
            SchemaLoader loader = SchemaLoader.builder()
                .addFormatValidator(customDateTimeValidator)
                .schemaJson(new JSONObject(validationSchema))
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
