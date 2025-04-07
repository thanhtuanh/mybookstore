package com.bookstore.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI bookstoreOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Book Store API")
                        .description("Spring Boot REST API für die Verwaltung von Büchern")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Bookstore Team")
                                .url("https://github.com/bookstore")
                                .email("info@bookstore.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Book Store Wiki Documentation")
                        .url("https://github.com/bookstore/wiki"));
    }
}
