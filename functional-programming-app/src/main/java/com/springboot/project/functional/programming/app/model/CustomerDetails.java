package com.springboot.project.functional.programming.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CustomerDetails {

    private UUID id;
    private String name;
    private String address;

}
