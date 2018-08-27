package com.gy.cloud.feign;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceHystrix implements HelloService{

  public String hello(String name) {
    return "error " + name;
  }

}
