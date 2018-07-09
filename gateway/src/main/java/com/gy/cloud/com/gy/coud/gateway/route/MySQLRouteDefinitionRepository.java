package com.gy.cloud.com.gy.coud.gateway.route;

import com.gy.cloud.com.gy.coud.gateway.dao.RouteDao;
import com.gy.cloud.com.gy.coud.gateway.domain.DCRouteDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
public class MySQLRouteDefinitionRepository implements RouteDefinitionRepository {

  private List<RouteDefinition> routeDefines;

  public MySQLRouteDefinitionRepository() {

//    routeDefines = new ArrayList();
//    RouteDefinition routeDefinition = new RouteDefinition("greet_hello=http://127.0.0.1:7100/greet, Path=/hello/**");
//    routeDefines.add(routeDefinition);
  }


  @Autowired
  private RouteDao routeDao;

  @Override
  public Flux<RouteDefinition> getRouteDefinitions() {

    List<RouteDefinition> routeDefinitions = this.findAll();
    return Flux.fromIterable(routeDefinitions);
  }

  @Override
  public Mono<Void> save(Mono<RouteDefinition> route) {
    return null;
  }

  @Override
  public Mono<Void> delete(Mono<String> routeId) {
    return null;
  }

  public List<RouteDefinition> findAll() {

    List<DCRouteDefinition> dcRouteDefinitions = routeDao.findAll();

    return convert2RouteDefinition(dcRouteDefinitions);
  }

  private List<RouteDefinition> convert2RouteDefinition(List<DCRouteDefinition> dcRouteDefinitions) {

    List<RouteDefinition> defines = new ArrayList<>();

    dcRouteDefinitions.forEach(dc -> {
      defines.add(new RouteDefinition(dc.getId() + "=" + dc.getUri() + ", " + dc.getPredicates()));
    });

    return defines;
  }

}
