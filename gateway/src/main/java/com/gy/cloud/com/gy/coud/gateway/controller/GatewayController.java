package com.gy.cloud.com.gy.coud.gateway.controller;//package com.gy.cloud.com.gy.coud.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GatewayController implements ApplicationEventPublisherAware {


  @Autowired
  private ApplicationEventPublisher publisher;


  @GetMapping("/hello")
  public Mono<ResponseEntity<byte[]>> proxy(ProxyExchange<byte[]> proxy) {

    return proxy.uri("http://127.0.0.1:7100/greet").get();
  }

  @GetMapping("/routes")
  public String getRoutes() {

    return "this is routes...";
  }

  @Override
  public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @PostMapping("/refresh")
  public Mono<Void> refresh() {
    System.out.println("/refresh...");
    this.publisher.publishEvent(new RefreshRoutesEvent(this));
    return Mono.empty();
  }
}
