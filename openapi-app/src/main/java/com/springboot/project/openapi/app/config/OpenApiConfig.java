package com.springboot.project.openapi.app.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi groupedOpenApiConfiguration() {
        return GroupedOpenApi.builder()
                .group("server")
                .pathsToMatch("/v1/**")
                .build();
    }

}
