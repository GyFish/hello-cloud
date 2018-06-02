package com.gy.cloud.eureka.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 声明这是一个Eureka Client
 */
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ServiceHello {

  public static void main(String[] args) {
    SpringApplication.run(ServiceHello.class, args);
  }

  @Value("${server.port}")
  private int serverPort = 0;

  @GetMapping("/hello")
  public String hello() {
    System.out.println("/hello");
    return "Hello, Spring Cloud! My port is " + String.valueOf(serverPort);
  }

}
