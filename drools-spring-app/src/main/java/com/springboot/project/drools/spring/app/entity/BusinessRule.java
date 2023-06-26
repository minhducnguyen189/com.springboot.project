package com.springboot.project.drools.spring.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@org.springframework.data.mongodb.core.mapping.Document("business_rules")
public class BusinessRule {

    @Id
    @NotNull
    private UUID id;
    @Indexed(unique = true)
    @NotNull(message = "name of schema can't be null")
    private String name;
    @NotNull(message = "value of schema can't be null")
    private Document value;

}
