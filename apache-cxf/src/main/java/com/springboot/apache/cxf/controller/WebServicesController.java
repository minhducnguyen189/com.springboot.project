package com.springboot.apache.cxf.controller;

import com.springboot.apache.cxf.generated.CountryNameResponse;
import com.springboot.apache.cxf.generated.ListOfCountryNamesGroupedByContinentResponse;
import com.springboot.apache.cxf.service.CountryCodeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebServicesController {

    private final CountryCodeHandler countryCodeHandler;

    @GetMapping(path = "/v1/soap/country/{countryISO}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CountryNameResponse> getCountryNameByISO(@PathVariable("countryISO") String countryISO) {
        return ResponseEntity.ok(this.countryCodeHandler.getCountryName(countryISO));
    }

    @GetMapping(path = "/v1/soap/country/listOfCountryNamesGroupedByContinent", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListOfCountryNamesGroupedByContinentResponse> getCountryNameByISO() {
        return ResponseEntity.ok(this.countryCodeHandler.getListOfCountryNamesGroupedByContinent());
    }

}
