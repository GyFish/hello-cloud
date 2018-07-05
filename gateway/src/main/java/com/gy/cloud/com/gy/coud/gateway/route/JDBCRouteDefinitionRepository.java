package com.gy.cloud.com.gy.coud.gateway.route;

import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class JDBCRouteDefinitionRepository implements RouteDefinitionRepository {

  @Override
  public Flux<RouteDefinition> getRouteDefinitions() {
    return null;
  }

  @Override
  public Mono<Void> save(Mono<RouteDefinition> route) {
    return null;
  }

  @Override
  public Mono<Void> delete(Mono<String> routeId) {
    return null;
  }

  public Mono find() {
    return null;
  }

}
