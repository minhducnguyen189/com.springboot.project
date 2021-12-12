package com.springboot.project.basicAuth.app.annotation;

import com.springboot.project.basicAuth.app.config.ApplicationConfig;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ApplicationConfig.class)
public @interface EnableBasicAuthentication {

    @AliasFor(annotation = Import.class, attribute = "value")
    Class<?>[] value() default {ApplicationConfig.class};

}
