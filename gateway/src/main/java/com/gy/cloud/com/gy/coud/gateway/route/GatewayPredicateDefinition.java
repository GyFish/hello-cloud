package com.gy.cloud.com.gy.coud.gateway.route;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
public class GatewayPredicateDefinition {

  // 断言对应的Name
  private String name;

  // 配置的断言规则
  private Map<String, String> args = new LinkedHashMap<>();

}
