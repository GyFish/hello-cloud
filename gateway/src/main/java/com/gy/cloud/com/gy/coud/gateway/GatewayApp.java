package com.gy.cloud.com.gy.coud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApp {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApp.class, args);
  }

//  @Bean
//  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//    return builder.routes()
//            .route(t -> t.path("/api/**")
//                    .and()
//                    .uri("http://localhost:7100"))
//            .build();
//  }

}
