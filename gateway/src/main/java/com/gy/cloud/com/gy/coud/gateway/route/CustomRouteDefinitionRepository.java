package com.gy.cloud.com.gy.coud.gateway.route;

import com.gy.cloud.com.gy.coud.gateway.dao.RouteDao;
import com.gy.cloud.com.gy.coud.gateway.domain.CustomRouteDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomRouteDefinitionRepository implements RouteDefinitionRepository {

  public CustomRouteDefinitionRepository() {
  }


  @Autowired
  private RouteDao routeDao;

  @Override
  public Flux<RouteDefinition> getRouteDefinitions() {

    List<RouteDefinition> routeDefinitions = this.findAll();
    return Flux.fromIterable(routeDefinitions);
  }

  public List<RouteDefinition> findAll() {

    List<CustomRouteDefinition> customRouteDefinitions = routeDao.findAll();

    return convert2RouteDefinition(customRouteDefinitions);
  }

  private List<RouteDefinition> convert2RouteDefinition(List<CustomRouteDefinition> customRouteDefinitions) {

    List<RouteDefinition> defines = new ArrayList<>();

    customRouteDefinitions.forEach(dc -> {
      defines.add(new RouteDefinition(dc.getId() + "=" + dc.getUri() + ", " + dc.getPredicates()));
    });

    return defines;
  }



  @Override
  public Mono<Void> save(Mono<RouteDefinition> route) {
    return null;
  }

  @Override
  public Mono<Void> delete(Mono<String> routeId) {
    return null;
  }

}
