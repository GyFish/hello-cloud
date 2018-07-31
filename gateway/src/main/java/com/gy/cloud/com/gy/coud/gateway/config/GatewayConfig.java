package com.gy.cloud.com.gy.coud.gateway.config;

import com.gy.cloud.com.gy.coud.gateway.filter.AuthFilter;
import com.gy.cloud.com.gy.coud.gateway.filter.ElapsedFilter;
import com.gy.cloud.com.gy.coud.gateway.route.CustomRouteDefinitionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

  @Bean
  public CustomRouteDefinitionRepository mySQLRouteDefinitionRepository() {
    return new CustomRouteDefinitionRepository();
  }

  @Bean
  public ElapsedFilter elapsedFilter(){
    return new ElapsedFilter();
  }

  @Bean
  public AuthFilter authFilter() {
    return new AuthFilter();
  }

}
