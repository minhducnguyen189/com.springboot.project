package com.springboot.apache.cxf.factory;

import com.springboot.apache.cxf.config.CountryInfoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UrlFactory {

    private final CountryInfoProperties countryInfoProperties;

    public String buildWebServiceUrl() {
        UriComponents uriComponents = UriComponentsBuilder
                .newInstance()
                .scheme(this.countryInfoProperties.getProtocol())
                .host(this.countryInfoProperties.getHostname())
                .port(this.countryInfoProperties.getPort())
                .path(this.countryInfoProperties.getApi())
                .build();
        return uriComponents.toString();
    }

}
