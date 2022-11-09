package com.dev.restfullapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    private final String securitySchemeName = "bearerAuth";

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(this.info())
                .addSecurityItem(new SecurityRequirement().addList(this.securitySchemeName))
                .components(
                        new Components().addSecuritySchemes(
                                this.securitySchemeName,
                                new SecurityScheme()
                                        .name(this.securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }

    private Info info() {
        return new Info()
                .title("Sales API")
                .description("Sales projects API")
                .version("v1")
                .license(this.license())
                .contact(this.contact());
    }

    private License license() {
        return new License()
                .name("Apache 2.0");
    }

    private Contact contact() {
        return new Contact()
                .name("Mario Junior")
                .email("drapalskimario@gmail.com")
                .url("https://github.com/drapalskiMario");
    }
}
