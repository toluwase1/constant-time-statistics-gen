package com.example.seerbit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;


@Configuration
public class SwaggerConfiguration {

    private ApiInfo apiInfo() {
        return new ApiInfo("Seerbit statistics",
                "Seerbit statistics System",
                "1.0",
                "Free to use",
                new Contact("Seerbit statistics", "www.seerbit.com", "seerbit@gmail.com"),
                "License of API",
                "www.seerbit.com",
                Collections.emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.serbit"))
                .paths(PathSelectors.any())
                .build();
    }
}
