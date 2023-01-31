package com.springboot.project.chronicle.queue.app.model;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class ErrorDetail {

    private UUID id;
    private Integer errorCode;
    private String errorMessage;
    private OffsetDateTime timestamp;

}
