package com.springboot.project.web.services.api;

import com.springboot.project.web.services.model.gen.CountryName;
import com.springboot.project.web.services.model.gen.CountryNameResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CountryClient extends WebServiceGatewaySupport {

    public CountryNameResponse getCountry(String countryISO) {
        CountryName request = new CountryName();
        request.setSCountryISOCode(countryISO);
        return (CountryNameResponse) getWebServiceTemplate()
                .marshalSendAndReceive(request);
    }

}
