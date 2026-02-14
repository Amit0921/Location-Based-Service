package com.amit.lbs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Location Based Service API")
                        .version("1.0.0")
                        .description("Spatial API for managing places and distance calculations")
                        .contact(new Contact()
                                .name("Amit Gupta")
                                .email("amitguptaa0921@gmail.com")
                                .url("https://www.linkedin.com/in/amitguptaa09/")
                        )
                );
    }
}
