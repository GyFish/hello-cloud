package com.gy.cloud.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.gy.cloud.security.handler.CustomLoginHandler;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.setAuthenticationSuccessHandler(new CustomLoginHandler());
    this.setAuthenticationFailureHandler(new CustomLoginHandler());
    this.setAuthenticationManager(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response)
          throws AuthenticationException {

    JSONObject reqObj = new JSONObject();
    try {
      InputStream is = request.getInputStream();
      String reqStr = IOUtils.toString(is, StandardCharsets.UTF_8);
      reqObj = JSONObject.parseObject(reqStr);
    } catch (IOException e) {
      System.out.println(">> 从 body 获取 username、password 失败！");
      e.printStackTrace();
    }
    String username = reqObj.getString("username");
    String password = reqObj.getString("password");

    if (username == null) {
      username = "";
    }

    if (password == null) {
      password = "";
    }

    username = username.trim();

    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

    // Allow subclasses to set the "details" property
    setDetails(request, authRequest);

    return this.getAuthenticationManager().authenticate(authRequest);

  }
}
