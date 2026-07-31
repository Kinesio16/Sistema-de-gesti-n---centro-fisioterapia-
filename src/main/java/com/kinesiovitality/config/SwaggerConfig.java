package com.kinesiovitality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenAPI() {

        final String securitySchemeName = "Bearer Authentication";

        return new OpenAPI()

                .info(new Info()

                        .title("Kinesio Vitality API")

                        .version("1.0")

                        .description("""
Sistema de Gestión para Centros de Fisioterapia.
Incluye autenticación JWT, gestión de usuarios,
pacientes, citas, tratamientos y sesiones.
""")

                        .contact(new Contact()

                                .name("Paul Arias")

                                .email("paularias2727@gmail.com")))

                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(securitySchemeName))

                .components(

                        new Components()

                                .addSecuritySchemes(

                                        securitySchemeName,

                                        new SecurityScheme()

                                                .name(securitySchemeName)

                                                .type(SecurityScheme.Type.HTTP)

                                                .scheme("bearer")

                                                .bearerFormat("JWT")));
    }
}