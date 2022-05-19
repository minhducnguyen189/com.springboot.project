package com.springboot.project.mustache.app.controller;

import com.springboot.project.mustache.app.service.MustacheTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MustacheController {

    @Autowired
    private MustacheTemplateService mustacheTemplateService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/mustache/transform", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> tranformJson(@RequestBody String body) {
        return new ResponseEntity<>(this.mustacheTemplateService.transform(body), HttpStatus.CREATED);
    }

}
