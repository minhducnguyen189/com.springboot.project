package com.springboot.project.basicAuth.app.config;

import com.springboot.project.cipher.app.annotation.EnableCipherApi;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCipherApi
@ComponentScan("com.springboot.project.basicAuth")
@EnableJpaRepositories("com.springboot.project.basicAuth.app.repository")
@EntityScan("com.springboot.project.basicAuth.app.entity")
public class ApplicationConfig {
}
