package com.gy.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootApplication
public class MongoApp implements CommandLineRunner {

  @Autowired
  MongoTemplate mongoTemplate;

  public static void main(String[] args) {
    SpringApplication.run(MongoApp.class, args);
  }


  @Override
  public void run(String... args) throws Exception {

    Msg msg = new Msg("niu");

    mongoTemplate.save(msg);

    List<Msg> msgs = mongoTemplate.findAll(Msg.class);

    msgs.forEach(System.out::println);
  }
}
