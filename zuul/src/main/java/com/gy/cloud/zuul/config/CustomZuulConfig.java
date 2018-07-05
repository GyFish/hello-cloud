package com.gy.cloud.zuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by gy on 2018/6/19.
 */
@Configuration
public class CustomZuulConfig {

    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private ServerProperties server;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    public CustomRouteLocator routeLocator() {
//        CustomRouteLocator routeLocator = new CustomRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
//        routeLocator.setJdbcTemplate(jdbcTemplate);
//        return routeLocator;
        return null;
    }

}
