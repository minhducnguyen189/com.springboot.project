package com.springboot.project.basicAuth.api;

import com.springboot.project.basicAuth.api.model.BasicAuthUserRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface BasicAuthControllerApi {

    @RequestMapping(method = RequestMethod.POST, path = "/v1/auth/user", produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String> createBasicAuthUser(@RequestBody BasicAuthUserRequest basicAuthUserRequest);

}
