package com.gy.cloud.com.gy.coud.gateway.config;

import com.gy.cloud.com.gy.coud.gateway.route.MySQLRouteDefinitionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

  @Bean
  public MySQLRouteDefinitionRepository mySQLRouteDefinitionRepository() {
    return new MySQLRouteDefinitionRepository();
  }

}
