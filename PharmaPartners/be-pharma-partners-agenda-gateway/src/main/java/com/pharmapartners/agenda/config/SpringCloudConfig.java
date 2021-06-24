package com.pharmapartners.agenda.config;

import reactor.core.publisher.Mono;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.path("/employees/**")
                        .uri("http://employee:5001"))
                .route("path_route", r -> r.path("/credentials/**")
                        .uri("http://credentials:5002"))
                .route("path_route", r -> r.path("/patients/**")
                        .uri("http://patient-records:5003"))
                .route("path_route", r -> r.path("/appointments/**")
                        .uri("http://appointments:5004"))
                .route("path_route", r -> r.path("/locations/**")
                        .uri("http://location:5005"))
                .build();
    }
}
