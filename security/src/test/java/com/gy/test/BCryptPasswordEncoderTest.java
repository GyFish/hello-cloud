package com.gy.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderTest {


  public static void main(String[] args) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    //加密"0"
    String encode = bCryptPasswordEncoder.encode("123");
    System.out.println(encode);
  }


}
