package com.springboot.project.cipher.app.config;

import com.springboot.project.cipher.app.model.EncryptionConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.springboot.project.cipher")
public class ApplicationConfig {

    @Bean
    @ConfigurationProperties(prefix = "cipher.encryption")
    private EncryptionConfig encryption() {
        return new EncryptionConfig();
    }

}
