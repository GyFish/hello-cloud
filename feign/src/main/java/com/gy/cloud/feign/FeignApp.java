package com.gy.cloud.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
public class FeignApp {

  public static void main(String[] args) {
    SpringApplication.run(FeignApp.class, args);
  }

  @Autowired
  private HelloService helloService;

  @GetMapping("/hello")
  public String index(String name) {

    return helloService.hello("");
    //    return "hello " + name + "，this is feign app";
  }
}
