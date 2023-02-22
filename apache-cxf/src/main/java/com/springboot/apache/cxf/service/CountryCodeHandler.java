package com.springboot.apache.cxf.service;

import com.springboot.apache.cxf.factory.CountryInfoFactory;
import com.springboot.apache.cxf.generated.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountryCodeHandler {

    private final CountryInfoServiceSoapType countryClient;
    private final CountryInfoFactory countryInfoFactory;

    public CountryNameResponse getCountryName(String countryISO) {
        try {
            String countryName = this.countryClient.countryName(countryISO);
            return this.countryInfoFactory.mapCountryNameResponse(countryName);
        } catch (Exception e) {
            BindingProvider bindingProvider = (BindingProvider) this.countryClient;
            log.info("Error Code: {}", bindingProvider.getResponseContext().get(MessageContext.HTTP_RESPONSE_CODE));
            throw new RuntimeException(e);
        }
    }

    public ListOfCountryNamesGroupedByContinentResponse getListOfCountryNamesGroupedByContinent() {
        try {
            ArrayOftCountryCodeAndNameGroupedByContinent response = this.countryClient.listOfCountryNamesGroupedByContinent();
            return this.countryInfoFactory.mapListOfCountryNamesGroupedByContinentResponse(response);
        } catch (Exception e) {
            BindingProvider bindingProvider = (BindingProvider) this.countryClient;
            log.info("Error Code: {}", bindingProvider.getResponseContext().get(MessageContext.HTTP_RESPONSE_CODE));
            throw new RuntimeException(e);
        }
    }

}
