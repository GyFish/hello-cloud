package com.gy.cloud.com.gy.coud.gateway.config;

import com.gy.cloud.com.gy.coud.gateway.filter.AuthFilter;
import com.gy.cloud.com.gy.coud.gateway.filter.ElapsedFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

  @Bean
  public ElapsedFilter elapsedFilter(){
    return new ElapsedFilter();
  }

  @Bean
  public AuthFilter authFilter() {
    return new AuthFilter();
  }


}
