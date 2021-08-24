package com.muxichallenge.core.openapi;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Terminal Application API")
                        .contact(new Contact().name("Alexsandro de Lima Silva").email("alexsandrolima.dev@gmail.com").url("https://alexlimacti.github.io"))
                        .description("Realization of the challenge offered by Muxi, a member of the Conductor Group"));
    }
}
