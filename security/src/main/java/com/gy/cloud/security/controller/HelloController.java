package com.gy.cloud.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/")
  public String index() {

    return "this is a spring security demo!";
  }


  @GetMapping("/hello")
  public String hello() {

    return "hello!";
  }

}
