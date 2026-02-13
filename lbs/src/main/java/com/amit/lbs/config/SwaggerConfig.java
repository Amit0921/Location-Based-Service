//package com.amit.lbs.config;
//
//import io.swagger.v3.oas.models.Components;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import io.swagger.v3.oas.models.security.SecurityRequirement;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class SwaggerConfig {
//
//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("LBS REST API's")
//                        .description("API Description")
//                        .version("1.0")
//                        .contact(new Contact()
//                                .name("Amit Gupta")
//                                .email("amitguptaa0921@gmail.com")
//                                .url("http://localhost:8080/swagger-ui/index.html"))
//                        .license(new License()
//                                .name("Website Local URL")
//                                .url("http://localhost:4200")));
//    }
//}
