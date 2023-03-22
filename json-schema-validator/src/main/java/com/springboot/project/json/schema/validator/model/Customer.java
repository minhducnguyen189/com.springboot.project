package com.springboot.project.json.schema.validator.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Getter
@Setter
@Document("customers")
public class Customer {

    @Id
    private Long id;
    private String fullName;
    private String email;
    private org.bson.Document data;

}
