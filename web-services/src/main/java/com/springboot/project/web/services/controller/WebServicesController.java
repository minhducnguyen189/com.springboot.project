package com.springboot.project.web.services.controller;

import com.springboot.project.web.services.model.gen.CountryNameResponse;
import com.springboot.project.web.services.service.CountryCodeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebServicesController {

    @Autowired
    private CountryCodeHandler countryCodeHandler;

    @GetMapping(path = "/v1/soap/country/{countryISO}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CountryNameResponse> getCountryNameByISO(@PathVariable("countryISO") String countryISO) {
        return ResponseEntity.ok(this.countryCodeHandler.getCountryName(countryISO));
    }

}
