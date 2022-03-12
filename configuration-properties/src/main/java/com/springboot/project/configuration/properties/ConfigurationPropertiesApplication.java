package com.springboot.project.configuration.properties;

import com.springboot.project.configuration.properties.model.Data2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(value = {Data2.class})
@ConfigurationPropertiesScan(value = {"com.springboot.project.configuration.properties.model"})
public class ConfigurationPropertiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationPropertiesApplication.class, args);
    }

}
