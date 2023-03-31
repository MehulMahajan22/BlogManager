package com.main.ProductApiGateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
public class AppConfig {
    @Bean
    public RouteLocator getRoute(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->p.path("/blog/**")
                        .uri("http://localhost:4500/*"))
                .route(p->p.path("/user/**")
                        .uri("http://localhost:4400/*"))
                .build();
    }
}