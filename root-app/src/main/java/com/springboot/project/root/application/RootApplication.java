package com.springboot.project.root.application;

import com.springboot.project.basicAuth.app.annotation.EnableBasicAuthentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBasicAuthentication
@SpringBootApplication
public class RootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RootApplication.class, args);
	}

}
