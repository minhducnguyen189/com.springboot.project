package com.springboot.apache.cxf.factory;

import com.springboot.apache.cxf.exception.TechnicalException;
import com.springboot.apache.cxf.generated.ArrayOftCountryCodeAndNameGroupedByContinent;
import com.springboot.apache.cxf.generated.CountryInfoServiceSoapType;
import com.springboot.apache.cxf.generated.CountryNameResponse;
import com.springboot.apache.cxf.generated.ListOfCountryNamesGroupedByContinentResponse;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

@Slf4j
@UtilityClass
public class CountryInfoFactory {

    public CountryNameResponse mapCountryNameResponse(String countryName) {
        CountryNameResponse countryNameResponse = new CountryNameResponse();
        countryNameResponse.setCountryNameResult(countryName);
        return countryNameResponse;
    }

    public ListOfCountryNamesGroupedByContinentResponse mapListOfCountryNamesGroupedByContinentResponse(ArrayOftCountryCodeAndNameGroupedByContinent response) {
        ListOfCountryNamesGroupedByContinentResponse listOfCountryNamesGroupedByContinentResponse = new ListOfCountryNamesGroupedByContinentResponse();
        listOfCountryNamesGroupedByContinentResponse.setListOfCountryNamesGroupedByContinentResult(response);
        return listOfCountryNamesGroupedByContinentResponse;
    }


    public TechnicalException buildTechnicalException(CountryInfoServiceSoapType client, Exception ex) {
        return new TechnicalException(
                ex.getMessage(),
                getResponseCode(client),
                getEndpoint(client),
                getResponseHeaders(client),
                ExceptionUtils.getRootCauseMessage(ex));
    }


    private Integer getResponseCode(CountryInfoServiceSoapType countryClient) {
        BindingProvider bindingProvider = (BindingProvider) countryClient;
        Integer responseCode = (Integer) bindingProvider.getResponseContext().get(MessageContext.HTTP_RESPONSE_CODE);
        log.info("Error Code: {}", responseCode);
        return responseCode;
    }


    private String getEndpoint(CountryInfoServiceSoapType countryClient) {
        BindingProvider bindingProvider = (BindingProvider) countryClient;
        String endpoint = (String) bindingProvider.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);;
        log.info("Error Endpoint: {}", endpoint);
        return endpoint;
    }

    private String getResponseHeaders(CountryInfoServiceSoapType countryClient) {
        BindingProvider bindingProvider = (BindingProvider) countryClient;
        Object objectHeaders = bindingProvider.getResponseContext().get(MessageContext.HTTP_RESPONSE_HEADERS);
        return String.valueOf(objectHeaders);
    }


}
