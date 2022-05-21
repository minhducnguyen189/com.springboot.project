package com.springboot.project.functional.programming.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerQueryParams {

    private UUID customerId;
    private String name;

}
