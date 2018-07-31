package com.gy.cloud.security.handler;

import com.gy.cloud.security.support.WebUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

  // Login Success
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
    System.out.println("User login successfully, name={}" + authentication.getName());
    WebUtil.responseText(response, WebUtil.resultOk("ok"));
  }

  // Login Failure
  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
    System.out.println(">> 登录失败！");
    WebUtil.responseText(response, WebUtil.resultOk(exception.getMessage()));
  }
}