package com.gy.cloud.com.gy.coud.gateway.route;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
@Slf4j
public class DynamicRouteServiceImplByNacos {

  private final DynamicRouteServiceImpl dynamicRouteService;

  private final RouteDefinitionRepository routeDefinitionRepository;

  @Autowired
  public DynamicRouteServiceImplByNacos(DynamicRouteServiceImpl dynamicRouteService, RouteDefinitionRepository routeDefinitionRepository) {
    dynamicRouteByNacosListener("csc-gateway", "csc");
    this.dynamicRouteService = dynamicRouteService;
    this.routeDefinitionRepository = routeDefinitionRepository;
  }

  /**
   * 监听Nacos Server下发的动态路由配置
   *
   * @param dataId
   * @param group
   */
  public void dynamicRouteByNacosListener(String dataId, String group) {

    try {

      ConfigService configService = NacosFactory.createConfigService("127.0.0.1:8848");
      String content = configService.getConfig(dataId, group, 5000);
      log.info("config = {}", content);

      configService.addListener(dataId, group, new Listener() {

        @Override
        public void receiveConfigInfo(String configInfo) {
          RouteDefinition definition = JSON.parseObject(configInfo, RouteDefinition.class);
          dynamicRouteService.update(definition);
        }

        @Override
        public Executor getExecutor() {
          return null;
        }

      });
    } catch (NacosException e) {
      //todo 提醒:异常自行处理此处省略
      e.printStackTrace();
    }
  }
}
