
package com.springboot.project.json.schema.validator.model;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@org.springframework.data.mongodb.core.mapping.Document("database_sequence")
public class DatabaseSequence {

    @Id
    @NotNull
    private String id;
    @Min(value = 1, message = "version must be started from 1")
    private Long version;

}