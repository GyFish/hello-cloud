package com.gy.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "msg")
public class Msg {

  @Id
  private String id;

  private String name;

  public Msg(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Msg{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}
