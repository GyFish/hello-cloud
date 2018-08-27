package com.gy.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@FeignClient(name = "greet", url = "localhost:7100", fallback = HelloServiceHystrix.class)
public interface HelloService {

  @RequestMapping(value = "/greet")
  String hello(@RequestParam(value = "name") String name);

}
