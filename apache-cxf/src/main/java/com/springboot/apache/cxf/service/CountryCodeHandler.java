package com.springboot.apache.cxf.service;

import com.springboot.apache.cxf.factory.CountryInfoFactory;
import com.springboot.apache.cxf.generated.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountryCodeHandler {

    private final CountryInfoServiceSoapType countryClient;

    public CountryNameResponse getCountryName(String countryISO) {
        try {
            String countryName = this.countryClient.countryName(countryISO);
            return CountryInfoFactory.mapCountryNameResponse(countryName);
        } catch (Exception e) {
            throw CountryInfoFactory.buildTechnicalException(countryClient, e);
        }
    }

    public ListOfCountryNamesGroupedByContinentResponse getListOfCountryNamesGroupedByContinent() {
        try {
            ArrayOftCountryCodeAndNameGroupedByContinent response = this.countryClient.listOfCountryNamesGroupedByContinent();
            return CountryInfoFactory.mapListOfCountryNamesGroupedByContinentResponse(response);
        } catch (Exception e) {
            throw CountryInfoFactory.buildTechnicalException(countryClient, e);
        }
    }

}
