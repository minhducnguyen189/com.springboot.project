package com.springboot.apache.cxf.config;

import com.springboot.apache.cxf.factory.UrlFactory;
import com.springboot.apache.cxf.generated.CountryInfoService;
import com.springboot.apache.cxf.generated.CountryInfoServiceSoapType;
import com.springboot.apache.cxf.generated.ObjectFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.BindingProvider;
import java.util.Map;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CountryInfoClientConfig {

    private final UrlFactory urlFactory;

    @Bean
    public CountryInfoServiceSoapType countryInfoServiceSoapType() {
        CountryInfoService countryInfoService = new CountryInfoService();
        CountryInfoServiceSoapType countryInfoServiceSoapType =  countryInfoService.getCountryInfoServiceSoap();
        Map<String, Object> requestContext = ((BindingProvider) countryInfoServiceSoapType).getRequestContext();
        requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, this.urlFactory.buildWebServiceUrl());
        return countryInfoServiceSoapType;
    }

    @Bean
    public ObjectFactory countryInfoServiceSoapTypeFactory() {
        return new ObjectFactory();
    }

}
