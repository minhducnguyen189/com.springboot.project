package com.springboot.apache.cxf.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "country.client.soap")
public class CountryInfoProperties {

    private String hostname;
    private String protocol;
    private String port;
    private String api;

}
