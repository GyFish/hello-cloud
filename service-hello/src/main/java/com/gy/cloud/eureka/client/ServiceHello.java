package com.gy.cloud.eureka.client;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 声明这是一个Eureka Client
 */
//@EnableDiscoveryClient
@SpringBootApplication
@RestController
@RefreshScope
//@NacosPropertySource(dataId = "service-hello", autoRefreshed = true, groupId = "csc")
public class ServiceHello {

  public static void main(String[] args) {
    SpringApplication.run(ServiceHello.class, args);
  }

  @Value("${greet:hello}")
  String greetStr;

  @GetMapping("/hello/greet")
  public String hello() {

    System.out.println("/hello");
    return greetStr;
  }

}
