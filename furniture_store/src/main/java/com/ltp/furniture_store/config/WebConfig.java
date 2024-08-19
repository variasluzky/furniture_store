package com.ltp.furniture_store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // This applies CORS to all paths
                .allowedOrigins("http://localhost:4200") // The URL of the Angular app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD") // Allowed HTTP methods from the frontend
                .allowedHeaders("*") // Allows all headers
                .allowCredentials(true) // Optional: if you need to include cookies/session info
                .maxAge(3600); // Cache the CORS preflight request to avoid repeated checks
    }
}