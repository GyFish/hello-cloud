package com.gy.cloud.com.gy.coud.gateway.dao;

import com.gy.cloud.com.gy.coud.gateway.domain.DCRouteDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RouteDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<DCRouteDefinition> findAll() {

    return jdbcTemplate.query(
            "SELECT * FROM `cloud_route`",
            new BeanPropertyRowMapper<>(DCRouteDefinition.class));
  }

}
