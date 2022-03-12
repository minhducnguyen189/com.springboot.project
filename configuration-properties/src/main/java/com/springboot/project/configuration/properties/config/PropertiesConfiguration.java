package com.springboot.project.configuration.properties.config;

import com.springboot.project.configuration.properties.model.Data4;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "data")
    public Data4 data4() {
        return new Data4();
    }

}
