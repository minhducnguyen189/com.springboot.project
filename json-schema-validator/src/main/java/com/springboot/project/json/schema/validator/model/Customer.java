package com.springboot.project.json.schema.validator.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Document("customers")
public class Customer {

    @Id
    private UUID id;
    @NotNull
    private String fullName;
    @NotNull
    private String email;
    private org.bson.Document data;

}
