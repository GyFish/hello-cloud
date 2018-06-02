package com.gy.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableZuulProxy
@SpringBootApplication
@RestController
public class ZuulServer {

  public static void main(String[] args) {
    SpringApplication.run(ZuulServer.class, args);
  }

  @GetMapping("/")
  public String zuul() {
    return "this is the zuul server";
  }

}
