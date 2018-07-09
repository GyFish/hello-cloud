package com.gy.cloud.com.gy.coud.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {

  @GetMapping("/greet")
  public String greet() {
    System.out.println("/hello");
    return "Hei, I am the gateway!";
  }
//
//  @GetMapping("/hello/greet")
//  public String hello() {
//    System.out.println("/hello");
//    return "Hello, I am the gateway!";
//  }

}
