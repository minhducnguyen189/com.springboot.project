package com.springboot.project.basicAuth.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicAuthUserRequest {

    private String username;
    private String password;

}
