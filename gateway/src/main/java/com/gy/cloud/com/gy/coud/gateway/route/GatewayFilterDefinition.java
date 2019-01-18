package com.gy.cloud.com.gy.coud.gateway.route;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
public class GatewayFilterDefinition {

  // Name
  private String name;

  // 对应的路由规则
  private Map<String, String> args = new LinkedHashMap<>();

}