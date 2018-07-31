package com.gy.cloud.security.handler;

import com.gy.cloud.security.support.WebUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AuthenticationEntryPoint, AccessDeniedHandler {

  // no auth
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
    WebUtil.responseText(response, WebUtil.result(401, authException.getMessage()));
  }

  // Access is denied
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
    WebUtil.responseText(response, WebUtil.result(401, accessDeniedException.getMessage()));
  }
}