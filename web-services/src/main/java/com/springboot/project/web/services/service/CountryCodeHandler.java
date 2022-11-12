package com.springboot.project.web.services.service;

import com.springboot.project.web.services.api.CountryClient;
import com.springboot.project.web.services.model.gen.CountryNameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryCodeHandler {

    @Autowired
    private CountryClient countryClient;

    public CountryNameResponse getCountryName(String countryISO) {
        return countryClient.getCountry(countryISO);
    }

}
