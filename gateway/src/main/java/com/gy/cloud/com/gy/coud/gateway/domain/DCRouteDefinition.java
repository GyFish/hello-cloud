package com.gy.cloud.com.gy.coud.gateway.domain;

import lombok.Data;

@Data
public class DCRouteDefinition {

  private String id;

  private String uri;

  private String predicates;

  private String filters;

  private int order;

}
