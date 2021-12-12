package com.springboot.project.root.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class RootController {

    @RequestMapping(method = RequestMethod.GET, path = "/v1/root/datetime", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getLocalDateTime() {
        return new ResponseEntity<>(LocalDateTime.now().toString(), HttpStatus.OK);
    }

}
