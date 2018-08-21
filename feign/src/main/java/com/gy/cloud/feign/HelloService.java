package com.gy.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "greet", url = "localhost:7100")
public interface HelloService {

  @RequestMapping(value = "/greet")
  String hello(@RequestParam(value = "name") String name);

}
