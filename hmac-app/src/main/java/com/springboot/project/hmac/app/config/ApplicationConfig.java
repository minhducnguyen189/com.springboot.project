package com.springboot.project.hmac.app.config;

import com.springboot.project.hmac.app.model.HmacConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.springboot.project.hmac"})
public class ApplicationConfig {

    @Bean
    @ConfigurationProperties(prefix = "hmac")
    private HmacConfig hmacConfig() {
        return new HmacConfig();
    }
}
