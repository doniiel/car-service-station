package com.daniyal.sto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI stoOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("STO Service API")
                        .description("API for Car Service Station Management")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Daniyal")
                                .email("daniyal@example.com")));
    }
}