package com.springboot.project.root.application;

import com.springboot.project.cipher.impl.annotation.EnableCipherApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCipherApi
@SpringBootApplication
public class RootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RootApplication.class, args);
	}

}
