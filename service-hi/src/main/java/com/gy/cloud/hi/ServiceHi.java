package com.gy.cloud.hi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gy on 2018/6/3.
 */
//@EnableEurekaClient
@SpringBootApplication
@RestController
public class ServiceHi {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHi.class, args);
    }

    @Value("${server.port}")
    private int serverPort = 0;

    @GetMapping("/greet")
    public String hello() {
        System.out.println("/hello");
        return "Hi, Spring Cloud! My port is " + String.valueOf(serverPort);
    }


    @GetMapping("/home")
    public String home() {
        System.out.println("/home");
        return "home...";
    }

}
