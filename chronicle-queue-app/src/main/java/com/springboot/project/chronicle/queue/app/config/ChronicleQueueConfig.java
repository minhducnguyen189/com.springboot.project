package com.springboot.project.chronicle.queue.app.config;

import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ChronicleQueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ChronicleQueueConfig {

    @Bean
    public Chronicle errorDetailQueue() throws IOException {
        final String basePath = System.getProperty("user.dir") + "/chronicle-queue-app" + "/error-detail-queue";
        return ChronicleQueueBuilder.indexed(basePath).build();
    }

    @Bean
    public Chronicle errorDetailQueueIndex() throws IOException {
        final String basePath = System.getProperty("user.dir") + "/chronicle-queue-app" + "/error-detail-queue-index";
        return ChronicleQueueBuilder.indexed(basePath).build();
    }

}
