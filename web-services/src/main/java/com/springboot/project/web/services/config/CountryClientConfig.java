package com.springboot.project.web.services.config;

import com.springboot.project.web.services.api.CountryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CountryClientConfig {
    /**
     `contextPath` is the `package` that contains all our `generated java classes`
     */
    @Value("${country.client.soap.package}")
    private String contextPath;

    /**
     `soapURl` is the `WebServiceUrl` that we call to
     */
    @Value("${country.client.soap.url}")
    private String soapURl ;


    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath(contextPath);
        return marshaller;
    }

    @Bean
    public CountryClient countryClient(Jaxb2Marshaller marshaller) {
        CountryClient client = new CountryClient();
        client.setDefaultUri(soapURl);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
