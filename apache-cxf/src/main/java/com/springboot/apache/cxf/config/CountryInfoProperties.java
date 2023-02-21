package com.springboot.apache.cxf.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class CountryInfoProperties {

    @Value("${country.client.soap.url}")
    private String webServiceUrl;

    @Value("${country.client.soap.hostName}")
    private String hostName;

    @Value("${country.client.soap.protocol}")
    private String protocol;

    @Value("${country.client.soap.port}")
    private String port;

    @Value("${country.client.soap.api}")
    private String api;

}
