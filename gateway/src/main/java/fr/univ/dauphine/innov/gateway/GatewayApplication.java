package fr.univ.dauphine.innov.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route( r-> r.path("/api/service/**")
                        .filters(f->f.rewritePath("/api/service/(?<remains>.*)","/${remains}")
                                .preserveHostHeader()
                        )
                        .uri("lb://service-operations")
                )
                .route(r -> r.path("/api/auth/**")
                        .filters(f -> f.rewritePath("/api/auth/(?<remains>.*)", "/${remains}")                                )
                        .uri("lb://service-auth")
                )
                .build();
    }

}
