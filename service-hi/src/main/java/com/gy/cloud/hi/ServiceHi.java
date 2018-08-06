package com.gy.cloud.hi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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

    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取所有服务
     */
    @GetMapping("/services")
    public Object services() {
        return discoveryClient.getInstances("service-hello");
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @GetMapping("/discover")
    public Object discover() {
        return loadBalancer.choose("service-hello").getUri().toString();
    }
}
