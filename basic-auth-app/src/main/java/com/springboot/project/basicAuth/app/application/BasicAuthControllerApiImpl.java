package com.springboot.project.basicAuth.app.application;

import com.springboot.project.basicAuth.api.BasicAuthControllerApi;
import com.springboot.project.basicAuth.api.model.BasicAuthUserRequest;
import com.springboot.project.basicAuth.app.service.BasicAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BasicAuthControllerApiImpl implements BasicAuthControllerApi {

    private final BasicAuthService basicAuthService;

    @Override
    public ResponseEntity<String> createBasicAuthUser(BasicAuthUserRequest basicAuthUserRequest) {
        return new ResponseEntity<>(basicAuthService.createUserRepository(basicAuthUserRequest), HttpStatus.CREATED);
    }
}
