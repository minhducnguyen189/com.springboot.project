package com.springboot.project.cipher.impl.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.springboot.project.cipher.impl.config.ApplicationConfig;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ApplicationConfig.class)
public @interface EnableCipherApi {
	
	Class<?>[] value() default {};
	
}
